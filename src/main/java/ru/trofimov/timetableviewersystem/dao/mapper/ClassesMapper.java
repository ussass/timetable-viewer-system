package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ClassesMapper implements RowMapper<Classes> {
    @Override
    public Classes mapRow(ResultSet resultSet, int i) throws SQLException {
        Classes classes = new Classes(
                resultSet.getLong("course_id"),
                resultSet.getLong("teacher_id"),
                resultSet.getLong("group_id"),
                resultSet.getLong("classroom_id"),
                resultSet.getLong("classroom_id"),
                new Date(resultSet.getLong("classes_date"))
        );
        classes.setId(resultSet.getLong("classes_id"));
        return classes;
    }
}
