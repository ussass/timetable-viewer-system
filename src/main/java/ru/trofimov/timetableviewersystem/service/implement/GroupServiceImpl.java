package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Group save(Group entity) throws SQLException {
        return groupDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Group findById(Long id) throws SQLException {
        return groupDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Group update(Group entity) throws SQLException {
        return groupDao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) throws SQLException {
        groupDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate) {
        return groupDao.getGroupTimetable(groupId, startDate, finishDate);
    }
}
