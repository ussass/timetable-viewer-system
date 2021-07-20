package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;

import java.util.List;

public interface GroupService extends Service<Group, Long> {
    List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate);
}
