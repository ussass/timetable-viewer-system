package ru.trofimov.timetableviewersystem.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.trofimov.timetableviewersystem.model.Lesson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LessonMapper implements RowMapper<Lesson> {
    @Override
    public Lesson mapRow(ResultSet resultSet, int i) throws SQLException {
        Lesson lesson = new Lesson(
                resultSet.getLong("course_id"),
                resultSet.getLong("user_id"),
                resultSet.getLong("group_id"),
                resultSet.getLong("classroom_id"),
                resultSet.getLong("lesson_slot_id"),
                resultSet.getInt("day_of_week")
        );
        lesson.setId(resultSet.getLong("lesson_id"));
        return lesson;
    }
}
