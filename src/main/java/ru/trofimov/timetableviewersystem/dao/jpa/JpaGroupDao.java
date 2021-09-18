//package ru.trofimov.timetableviewersystem.dao.jpa;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Repository;
//import ru.trofimov.timetableviewersystem.dao.AbstractDao;
//import ru.trofimov.timetableviewersystem.dao.GroupDao;
//import ru.trofimov.timetableviewersystem.model.Group;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class JpaGroupDao extends AbstractDao<Group> implements GroupDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(JpaGroupDao.class);
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Group create(Group entity) throws SQLException {
//        try {
//            entityManager.persist(entity);
//            return entity;
//        } catch (DataAccessException e) {
//            logger.error("Unable to insert into groups {} due " + e.getMessage(), entity);
//            throw new SQLException("Unable to insert into groups due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public Group update(Group entity) throws SQLException {
//        try {
//            return entityManager.merge(entity);
//        } catch (DataAccessException e) {
//            logger.error("Unable to update {} due " + e.getMessage(), entity);
//            throw new SQLException("Unable to update group due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<Group> findAll() throws SQLException {
//        try {
//            return entityManager.createQuery("from " + Group.class.getName()).getResultList();
//        } catch (DataAccessException e) {
//            logger.error("Unable to find all groups due " + e.getMessage());
//            throw new SQLException("Unable to find all groups due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public Group findById(Long id) throws SQLException {
//        try {
//            return entityManager.find(Group.class, id);
//        } catch (DataAccessException e) {
//            logger.error("Unable to find group by id {} due " + e.getMessage(), id);
//            throw new SQLException("Unable to find group by id due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void delete(Long id) throws SQLException {
//        try {
//            entityManager.createQuery("delete from Group where id=:id")
//                    .setParameter("id", id)
//                    .executeUpdate();
//        } catch (DataAccessException e) {
//            logger.error("Unable to delete group with id {} due " + e.getMessage(), id);
//            throw new SQLException("Unable to delete group due " + e.getMessage(), e);
//        }
//    }
//}
//
