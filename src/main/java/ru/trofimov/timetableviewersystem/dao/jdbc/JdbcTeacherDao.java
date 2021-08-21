package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.TeacherDao;
import ru.trofimov.timetableviewersystem.dao.mapper.TeacherMapper;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcTeacherDao extends AbstractDao<Teacher> implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcTeacherDao.class);

    public JdbcTeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher create(Teacher entity) throws SQLException {
        String sql = "INSERT INTO teachers(first_name, last_name) VALUES (?, ?) RETURNING teacher_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entity.getFirstName());
                ps.setString(2, entity.getLastName());
                return ps;
            }, keyHolder);

            Teacher teacher = new Teacher(entity.getFirstName(), entity.getLastName());
            teacher.setId(keyHolder.getKey().longValue());
            return teacher;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into teachers {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into teachers due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Teacher> findAll() throws SQLException {
        String sql = "SELECT * from teachers";

        try {
            return jdbcTemplate.query(sql, new TeacherMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all teachers due " + e.getMessage());
            throw new SQLException("Unable to find all teachers due " + e.getMessage(), e);
        }
    }

    @Override
    public Teacher findById(Long id) throws SQLException {
        String sql = "SELECT * FROM teachers WHERE teacher_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TeacherMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find teacher by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find teacher by id due " + e.getMessage(), e);
        }
    }

    @Override
    public Teacher update(Teacher entity) throws SQLException {
        String sql = "UPDATE teachers SET first_name = ?, last_name = ? WHERE teacher_id = ?";

        try {
            jdbcTemplate.update(sql, entity.getFirstName(), entity.getLastName(), entity.getId());
            Teacher teacher = new Teacher(entity.getFirstName(), entity.getLastName());
            teacher.setId(entity.getId());
            return teacher;
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update teacher due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete teacher with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete teacher due " + e.getMessage(), e);
        }
    }
}
