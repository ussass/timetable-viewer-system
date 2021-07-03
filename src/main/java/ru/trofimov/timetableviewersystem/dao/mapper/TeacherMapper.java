package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        Teacher teacher = new Teacher(
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        teacher.setId(resultSet.getLong("teacher_id"));
        return teacher;
    }
}
