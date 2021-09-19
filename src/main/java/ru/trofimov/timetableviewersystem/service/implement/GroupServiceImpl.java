package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.model.Group;
import ru.trofimov.timetableviewersystem.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GroupServiceImpl implements GroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    @Transactional
    public Group save(Group entity) {
        Group group = groupDao.save(entity);
        logger.info("saved new group with id={}", group.getId());
        return group;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findAll() {
        logger.info("Got all groups");
        return StreamSupport.stream(groupDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Group findById(Long id) {
        logger.info("Got group by id = {}", id);
        return groupDao.findById(id).get();
    }

    @Override
    @Transactional
    public Group update(Group entity) {
        logger.info("updated group with id = {}", entity.getId());
        return groupDao.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("deleted group with id = {}", id);
        groupDao.deleteById(id);
    }
}
