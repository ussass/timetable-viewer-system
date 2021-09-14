package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaGroupDao extends AbstractDao<Group> implements GroupDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaGroupDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Group create(Group entity) throws SQLException {
        return null;
    }

    @Override
    public Group update(Group entity) throws SQLException {
        return null;
    }

    @Override
    public List<Group> findAll() throws SQLException {
        return null;
    }

    @Override
    public Group findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
