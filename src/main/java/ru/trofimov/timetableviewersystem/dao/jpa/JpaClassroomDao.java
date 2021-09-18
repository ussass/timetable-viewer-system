//package ru.trofimov.timetableviewersystem.dao.jpa;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Repository;
//import ru.trofimov.timetableviewersystem.dao.AbstractDao;
//import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
//import ru.trofimov.timetableviewersystem.model.Classroom;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class JpaClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(JpaClassroomDao.class);
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public Classroom create(Classroom entity) throws SQLException {
//        try {
//            entityManager.persist(entity);
//            return entity;
//        } catch (DataAccessException e) {
//            logger.error("Unable to insert into classrooms {} due " + e.getMessage(), entity);
//            throw new SQLException("Unable to insert into classrooms due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public Classroom update(Classroom entity) throws SQLException {
//        try {
//            return entityManager.merge(entity);
//        } catch (DataAccessException e) {
//            logger.error("Unable to update {} due " + e.getMessage(), entity);
//            throw new SQLException("Unable to update classroom due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<Classroom> findAll() throws SQLException {
//        try {
//            return entityManager.createQuery("from " + Classroom.class.getName()).getResultList();
//        } catch (DataAccessException e) {
//            logger.error("Unable to find all classrooms due " + e.getMessage());
//            throw new SQLException("Unable to find all classrooms due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public Classroom findById(Long id) throws SQLException {
//        try {
//            return entityManager.find(Classroom.class, id);
//        } catch (DataAccessException e) {
//            logger.error("Unable to find classroom by id {} due " + e.getMessage(), id);
//            throw new SQLException("Unable to find classroom by id due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void delete(Long id) throws SQLException {
//        try {
//            entityManager.createQuery("delete from Classroom where id=:id")
//                    .setParameter("id", id)
//                    .executeUpdate();
//        } catch (DataAccessException e) {
//            logger.error("Unable to delete classroom with id {} due " + e.getMessage(), id);
//            throw new SQLException("Unable to delete classroom due " + e.getMessage(), e);
//        }
//    }
//}
