package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.TeacherDao;
import ru.trofimov.timetableviewersystem.dao.mapper.ClassesMapper;
import ru.trofimov.timetableviewersystem.dao.mapper.TeacherMapper;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcTeacherDao extends AbstractDao<Teacher> implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher create(Teacher entity) throws SQLException {
        String sql = "INSERT INTO teachers(first_name, last_name) VALUES (?, ?) RETURNING teacher_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            Teacher teacher = new Teacher(entity.getFirstName(), entity.getLastName());
            teacher.setId(keyHolder.getKey().longValue());
            return teacher;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public Teacher update(Teacher entity) throws SQLException {
        String sql = "UPDATE teachers SET first_name = ?, last_name = ? WHERE teacher_id = ?";
        int update = jdbcTemplate.update(sql, entity.getFirstName(), entity.getLastName(), entity.getId());
        if (update == 1) {
            Teacher teacher = new Teacher(entity.getFirstName(), entity.getLastName());
            teacher.setId(entity.getId());
            return teacher;
        }
        throw new SQLException("Unable to update entity");
    }

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT * from teachers";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }

    @Override
    public Teacher findById(Long id) {
        String sql = "SELECT * FROM teachers WHERE teacher_id = ?";
        Teacher teacher = null;
        try {
            teacher = jdbcTemplate.queryForObject(sql, new Object[]{id}, new TeacherMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 0) {
            throw new SQLException("Unable to delete entity");
        }
    }

    @Override
    public List<Classes> getTeacherTimetable(long teacherId, long startDate, long finishDate) {
        String sql = "SELECT * FROM classes WHERE teacher_id = ? AND classes_date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new ClassesMapper(), teacherId, startDate, finishDate);
    }
}
