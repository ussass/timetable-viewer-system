package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface GroupDao extends Dao<Group, Long> {
    List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate) throws SQLException;
}
