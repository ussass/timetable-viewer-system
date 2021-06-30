package ru.trofimov.timetableviewersystem.dao.mapper;

import ru.trofimov.timetableviewersystem.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Student(
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    }
}
