package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.Lesson;

import java.util.List;

@Repository
public interface LessonDao extends CrudRepository<Lesson, Long> {

    List<Lesson> findByDayOfWeek(int dayOfWeek);

    void deleteByDayOfWeek(int dayOfWeek);
}
