package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Classroom;

import java.sql.SQLException;
import java.util.List;

public interface ClassroomDao extends Dao<Classroom, Long> {
    List<Classes> getClassroomTimetable(long classroomId, long startDate, long finishDate) throws SQLException;
}
