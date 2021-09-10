package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.UserGroupDao;
import ru.trofimov.timetableviewersystem.model.UserGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Repository
public class JpaUserGroupDao implements UserGroupDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaUserGroupDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserGroup save(UserGroup entity) throws SQLException {
        return null;
    }

    @Override
    public UserGroup update(UserGroup entity) throws SQLException {
        return null;
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {

    }

    @Override
    public void deleteByGroupId(Long id) throws SQLException {

    }
}
