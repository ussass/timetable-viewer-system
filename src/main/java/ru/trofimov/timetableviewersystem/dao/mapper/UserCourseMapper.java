package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCourseMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher = new Teacher(
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        teacher.setId(resultSet.getLong("user_id"));
        teacher.setLogin(resultSet.getString("login"));
        teacher.setPassword(resultSet.getString("password"));
        teacher.setStringRoles(resultSet.getString("roles"));
        teacher.setCourseId(resultSet.getLong("course_id"));
        teacher.setCourseName(resultSet.getString("course_name"));
        return teacher;
    }
}
