package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.dao.mapper.StudentMapper;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.implement.ClassesServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcStudentDao extends AbstractDao<Student> implements StudentDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public JdbcStudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student create(Student entity) throws SQLException {
        String sql = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?) RETURNING student_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, entity.getGroupId());
                ps.setString(2, entity.getFirstName());
                ps.setString(3, entity.getLastName());
                return ps;
            }, keyHolder);

            Student student = new Student(entity.getFirstName(), entity.getLastName());
            student.setGroupId(entity.getGroupId());
            student.setId(keyHolder.getKey().longValue());
            return student;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into students {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into students due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> findAll() throws SQLException {
        String sql = "SELECT * from students";

        try {
            return jdbcTemplate.query(sql, new StudentMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all students due " + e.getMessage());
            throw new SQLException("Unable to find all students due " + e.getMessage(), e);
        }
    }

    @Override
    public Student findById(Long id) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find student by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find student by id due " + e.getMessage(), e);
        }
    }

    @Override
    public Student update(Student entity) throws SQLException {

        String sql = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";

        try {
            jdbcTemplate.update(sql, entity.getGroupId(), entity.getFirstName(), entity.getLastName(), entity.getId());
            Student student = new Student(entity.getFirstName(), entity.getLastName());
            student.setGroupId(entity.getGroupId());
            student.setId(entity.getId());
            return student;
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update student due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete student with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete student due " + e.getMessage(), e);
        }
    }
}

