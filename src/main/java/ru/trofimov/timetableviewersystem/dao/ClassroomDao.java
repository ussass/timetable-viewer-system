package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Classroom;

import java.util.List;

public interface ClassroomDao extends Dao<Classroom, Long> {
    List<Classroom> findAllTest();
}
