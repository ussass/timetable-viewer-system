package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClassesMapper implements RowMapper<Classes> {
    @Override
    public Classes mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Classes(
                resultSet.getInt("course_id"),
                resultSet.getLong("teacher_id"),
                resultSet.getLong("group_id"),
                resultSet.getInt("classroom_id"),
                resultSet.getInt("classroom_id"),
                new Date(resultSet.getLong("classes_date"))
                );
    }
}
