package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.UserGroupDao;
import ru.trofimov.timetableviewersystem.model.UserGroup;
import ru.trofimov.timetableviewersystem.service.UserGroupService;

import java.sql.SQLException;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    private static final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class);

    private final UserGroupDao userGroupDao;

    public UserGroupServiceImpl(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    @Override
    public UserGroup save(UserGroup entity) throws SQLException {
        UserGroup userGroup = userGroupDao.save(entity);
        logger.info("saved new user_group with userId={} and groupId ={}", userGroup.getUserId(), userGroup.getGroupId());
        return userGroup;
    }

    @Override
    public UserGroup update(UserGroup entity) throws SQLException {
        logger.info("updated user_group with userId={} and groupId ={}", entity.getUserId(), entity.getGroupId());
        return userGroupDao.update(entity);
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {
        logger.info("deleted user_group with userId = {}", id);
        userGroupDao.deleteByUserId(id);
    }

    @Override
    public void deleteByGroupId(Long id) throws SQLException {
        logger.info("deleted user_group with groupId = {}", id);
        userGroupDao.deleteByGroupId(id);
    }
}
