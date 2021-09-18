//package ru.trofimov.timetableviewersystem.dao.jpa;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//import ru.trofimov.timetableviewersystem.dao.AbstractDao;
//import ru.trofimov.timetableviewersystem.dao.LessonSlotDao;
//import ru.trofimov.timetableviewersystem.model.LessonSlot;
//import ru.trofimov.timetableviewersystem.model.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class JpaLessonSlotDao extends AbstractDao<LessonSlot> implements LessonSlotDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(JpaLessonSlotDao.class);
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public LessonSlot create(LessonSlot entity) throws SQLException {
//        try {
//            entityManager.persist(entity);
//            return entity;
//        } catch (Exception e) {
//            logger.error("Unable to insert into lesson_slot {} due " + e.getMessage(), entity);
//            throw new SQLException("Unable to insert into lesson_slot due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public LessonSlot update(LessonSlot entity) throws SQLException {
//        try {
//            return entityManager.merge(entity);
//        } catch (Exception e) {
//            logger.error("Unable to update {} due " + e.getMessage(), entity);
//            throw new SQLException("Unable to update lesson slot due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<LessonSlot> findAll() throws SQLException {
//        try {
//            return entityManager.createQuery("from " + LessonSlot.class.getName()).getResultList();
//        } catch (Exception e) {
//            logger.error("Unable to find all lesson slots due " + e.getMessage());
//            throw new SQLException("Unable to find all lesson slots due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public LessonSlot findById(Long id) throws SQLException {
//        try {
//            return entityManager.find(LessonSlot.class, id);
//        } catch (Exception e) {
//            logger.error("Unable to find lesson slot by id {} due " + e.getMessage(), id);
//            throw new SQLException("Unable to find lesson slot by id due " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void delete(Long id) throws SQLException {
//        try {
//            entityManager.createQuery("delete from LessonSlot where id=:id")
//                    .setParameter("id", id)
//                    .executeUpdate();
//        } catch (Exception e) {
//            logger.error("Unable to delete lesson slot with id {} due " + e.getMessage(), id);
//            throw new SQLException("Unable to delete lesson slot due " + e.getMessage(), e);
//        }
//    }
//}
