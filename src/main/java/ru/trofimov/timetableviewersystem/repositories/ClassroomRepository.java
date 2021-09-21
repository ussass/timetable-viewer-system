package ru.trofimov.timetableviewersystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.Classroom;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
}
