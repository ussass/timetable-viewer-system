package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGroupMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student(
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        student.setId(resultSet.getLong("user_id"));
        student.setLogin(resultSet.getString("login"));
        student.setPassword(resultSet.getString("password"));
        student.setStringRoles(resultSet.getString("roles"));
        student.setGroupId(resultSet.getLong("group_id"));
        student.setGroupName(resultSet.getString("group_name"));
        return student;
    }
}
