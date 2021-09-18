package ru.trofimov.timetableviewersystem.dao;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.timetableviewersystem.model.Course;

public interface CourseDao extends CrudRepository<Course, Long> {
}
