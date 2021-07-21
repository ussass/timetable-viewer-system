package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.util.List;

public interface TeacherDao extends Dao<Teacher, Long> {
    List<Classes> getTeacherTimetable(long teacherId, long startDate, long finishDate);
}
