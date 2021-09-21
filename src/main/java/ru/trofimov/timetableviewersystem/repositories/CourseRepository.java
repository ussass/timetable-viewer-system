package ru.trofimov.timetableviewersystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.model.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
}
