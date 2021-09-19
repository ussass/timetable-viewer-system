package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.Lesson;

import java.sql.SQLException;
import java.util.List;

public interface LessonService extends CrudService<Lesson, Long> {
    List<Lesson> saveAll(List<Lesson> lessons);

    List<Lesson> getLessonsForDay(int day) throws SQLException;

    void deleteByDay(int day) throws SQLException;
}
