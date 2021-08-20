package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.Lesson;

import java.util.List;

public interface LessonService extends Service<Lesson, Long>{
    List<Lesson> saveAll(List<Lesson> lessons);
}
