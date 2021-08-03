package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;

import java.sql.SQLException;
import java.util.List;

public interface GroupService extends Service<Group, Long> {
    List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate) throws SQLException;
}
