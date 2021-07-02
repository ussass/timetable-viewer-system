package ru.trofimov.timetableviewersystem.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.mapper.StudentMapper;
import ru.trofimov.timetableviewersystem.model.Student;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class JdbcStudentDao extends AbstractDao<Student> implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Student create(Student entity) {
        String sql = "INSERT INTO students(group_id, first_name, last_name) VALUES (0, 'asd', ?);" +
                "CALL Scope_IDENTITY() ;";

       KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection
          .prepareStatement(sql);
          ps.setString(1, entity.getLastName());
          return ps;
        }, keyHolder);

//        return (long) keyHolder.getKey();

        System.out.println((long) keyHolder.getKey());

        return null;
    }

    @Override
    public Student update(Student entity) {
        System.out.println("update");
        return null;
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
    public void delete(Long id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
