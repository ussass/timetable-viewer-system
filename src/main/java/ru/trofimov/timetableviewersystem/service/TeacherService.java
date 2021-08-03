package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService extends Service<Teacher, Long> {
    List<Classes> getTeacherTimetable(long teacherId, long startDate, long finishDate) throws SQLException;
}
