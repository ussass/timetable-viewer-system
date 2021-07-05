package ru.trofimov.timetableviewersystem.dao.mapper;

import ru.trofimov.timetableviewersystem.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student(
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        student.setId(resultSet.getLong("student_id"));
        student.setGroupId(resultSet.getInt("group_id"));

        return student;
    }
}
