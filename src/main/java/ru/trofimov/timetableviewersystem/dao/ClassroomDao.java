package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.timetableviewersystem.model.Classroom;

public interface ClassroomDao extends CrudRepository<Classroom, Long> {
}
