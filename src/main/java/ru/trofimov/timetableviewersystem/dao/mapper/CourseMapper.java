package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"));
    }
}
