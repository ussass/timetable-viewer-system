package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group save(Group entity) throws SQLException {
        return groupDao.save(entity);
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public Group findById(Long id) throws SQLException {
        return groupDao.findById(id);
    }

    @Override
    public Group update(Group entity) throws SQLException {
        return groupDao.update(entity);
    }

    @Override
    public void delete(Long id) throws SQLException {
        groupDao.delete(id);
    }

    @Override
    public List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate) {
        return groupDao.getGroupTimetable(groupId, startDate, finishDate);
    }
}
