package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    @Transactional
    public Group save(Group entity) throws SQLException {
        Group group = groupDao.save(entity);
        logger.info("saved new group with id={}", group.getId());
        return group;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findAll() {
        logger.info("Got all groups");
        return groupDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Group findById(Long id) throws SQLException {
        logger.info("Got group by id = {}", id);
        return groupDao.findById(id);
    }

    @Override
    @Transactional
    public Group update(Group entity) throws SQLException {
        logger.info("updated group with id = {}", entity.getId());
        return groupDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted group with id = {}", id);
        groupDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate) {
        logger.info("Got schedule for group with id = {} for dates {} - {}", groupId, startDate, finishDate);
        return groupDao.getGroupTimetable(groupId, startDate, finishDate);
    }
}
