package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.timetableviewersystem.model.Lesson;

public interface LessonCrudDao extends CrudRepository<Lesson, Long> {
}
