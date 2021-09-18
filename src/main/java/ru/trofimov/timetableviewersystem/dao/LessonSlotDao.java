package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

public interface LessonSlotDao extends CrudRepository<LessonSlot, Long> {
}
