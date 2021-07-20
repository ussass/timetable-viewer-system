package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Classroom;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassroomMapper implements RowMapper<Classroom> {
    @Override
    public Classroom mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Classroom(resultSet.getInt("classroom_id"), resultSet.getInt("classroom_number"));
    }
}
