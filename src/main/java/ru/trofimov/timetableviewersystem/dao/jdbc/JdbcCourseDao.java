package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.dao.mapper.CourseMapper;
import ru.trofimov.timetableviewersystem.model.Course;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcCourseDao extends AbstractDao<Course> implements CourseDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course create(Course entity) throws SQLException {
        String sql = "INSERT INTO courses(course_name) VALUES (?) RETURNING course_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getCourseName());
            return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            Course course = new Course(entity.getCourseName());
            course.setId(keyHolder.getKey().longValue());
            return course;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public List<Course> findAll() {
        String sql = "SELECT * from courses";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public Course findById(Long id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CourseMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        throw new SQLException("Unable to find by id entity");
    }

    @Override
    public Course update(Course entity) throws SQLException {
        String sql = "UPDATE courses SET course_name = ? WHERE course_id = ?";
        int update = jdbcTemplate.update(sql, entity.getCourseName(), entity.getId());
        if (update == 1) {
            Course course = new Course(entity.getCourseName());
            course.setId(entity.getId());
            return course;
        }
        throw new SQLException("Unable to update entity");
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 0) {
            throw new SQLException("Unable to delete entity");
        }
    }
}
