package ru.trofimov.timetableviewersystem.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.mapper.StudentMapper;
import ru.trofimov.timetableviewersystem.model.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcStudentDao extends AbstractDao<Student> implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student create(Student entity) throws SQLException {
        String sql = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection
          .prepareStatement(sql);
          ps.setLong(1, entity.getGroupId());
          ps.setString(2, entity.getFirstName());
          ps.setString(3, entity.getLastName());
          return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            Student student = new Student(entity.getFirstName(), entity.getLastName());
            student.setGroupId(entity.getGroupId());
            student.setId(keyHolder.getKey().longValue());
            return student;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public Student update(Student entity) throws SQLException {

        String sql = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";
        int update = jdbcTemplate.update(sql, entity.getGroupId(), entity.getFirstName(), entity.getLastName(), entity.getId());
        if (update == 1){
            Student student = new Student(entity.getFirstName(), entity.getLastName());
            student.setGroupId(entity.getGroupId());
            student.setId(entity.getId());
            return student;
        }
        throw new SQLException("Unable to update entity");
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * from students";
        return jdbcTemplate.query(sql, new StudentMapper());
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 0){
            throw new SQLException("Unable to delete entity");
        }
    }
}
