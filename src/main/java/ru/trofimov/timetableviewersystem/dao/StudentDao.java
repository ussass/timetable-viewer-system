package ru.trofimov.timetableviewersystem.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.mapper.StudentMapper;
import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;

@Component
public class StudentDao implements Dao<Student> {
    private final JdbcTemplate jdbcTemplate;

    public StudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Student student) {
        String sql = "INSERT INTO students(group_id, first_name, last_name) VALUES (?, ?, ?)";
        int id = jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName());
//        System.out.println(id + "!!!");
//        student.setStudentId(id);
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * from students";
        return jdbcTemplate.query(sql, new StudentMapper());
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * from students WHERE student_id = ?";
        Student student = null;
        try {
            student = jdbcTemplate.queryForObject(sql, new Object[]{id}, new StudentMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void update(Student student, int id) {
        String sql = "UPDATE students SET group_id = ?, first_name = ?, last_name = ? WHERE student_id = ?";
        jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName(), id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from STUDENTS where STUDENT_ID = ?";
        jdbcTemplate.update(sql, id);

    }
}
