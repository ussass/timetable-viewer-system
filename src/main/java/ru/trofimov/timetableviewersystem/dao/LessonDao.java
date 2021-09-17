package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface LessonDao extends Dao<Lesson, Long> {
    List<Lesson> getLessonsForDay(int day) throws SQLException;

    void deleteByDay(int day) throws SQLException;
}
