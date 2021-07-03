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
//        String sql = "INSERT INTO students(group_id, first_name, last_name) VALUES (0, 'asd', ?);" +
//                "CALL Scope_IDENTITY() ;";
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

        System.out.println(keyHolder.getKey());

        if (updatedRows == 1) {
            Student student = new Student(entity.getFirstName(), entity.getLastName());
            student.setGroupId(entity.getGroupId());
            student.setId(keyHolder.getKey().longValue());
            return student;
        }
        throw new SQLException("Unable to insert entity");

//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        int updatedRows = jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection
//                    .prepareStatement("insert into dogs (name, age) values (?,?)");
//            ps.setString(1, dog.getName());
//            ps.setInt(2, dog.getAge());
//            return ps;
//        }, keyHolder);
//
//        if (updatedRows == 1) {
//            return new Dog(
//                    keyHolder.getKey().longValue(),
//                    dog.getName(),
//                    dog.getAge()
//            );
//        }
//        throw new SQLException("Unable to insert entity");
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
