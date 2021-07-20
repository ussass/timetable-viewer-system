package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonSlotMapper implements RowMapper<LessonSlot> {
    @Override
    public LessonSlot mapRow(ResultSet resultSet, int i) throws SQLException {
        return new LessonSlot(resultSet.getInt("lesson_slot_id"), resultSet.getInt("lesson_slot_number"));
    }
}
