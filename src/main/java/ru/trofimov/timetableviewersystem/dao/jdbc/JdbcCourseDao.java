package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.dao.mapper.CourseMapper;
import ru.trofimov.timetableviewersystem.model.Course;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcCourseDao extends AbstractDao<Course> implements CourseDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcCourseDao.class);

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course create(Course entity) throws SQLException {
        String sql = "INSERT INTO courses(course_name) VALUES (?) RETURNING course_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entity.getCourseName());
                return ps;
            }, keyHolder);

            Course course = new Course(entity.getCourseName());
            course.setId(keyHolder.getKey().longValue());
            return course;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into courses {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into courses due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> findAll() throws SQLException {
        String sql = "SELECT * from courses";

        try {
            return jdbcTemplate.query(sql, new CourseMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all courses due " + e.getMessage());
            throw new SQLException("Unable to find all courses {} due " + e.getMessage(), e);
        }
    }

    @Override
    public Course findById(Long id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CourseMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find course by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find course by id due " + e.getMessage(), e);
        }
    }

    @Override
    public Course update(Course entity) throws SQLException {
        String sql = "UPDATE courses SET course_name = ? WHERE course_id = ?";

        try {
            jdbcTemplate.update(sql, entity.getCourseName(), entity.getId());
            Course course = new Course(entity.getCourseName());
            course.setId(entity.getId());
            return course;
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update course due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete course with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete course due " + e.getMessage(), e);
        }
    }
}
