package ru.trofimov.timetableviewersystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

@Repository
public interface LessonSlotRepository extends CrudRepository<LessonSlot, Long> {
}
