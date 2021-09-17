package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.LessonDao;
import ru.trofimov.timetableviewersystem.model.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaLessonDao extends AbstractDao<Lesson> implements LessonDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaLessonDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Lesson create(Lesson entity) throws SQLException {
        try {
            entityManager.persist(entity);
            return entity;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into lessons {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into lessons due " + e.getMessage(), e);
        }

    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        try {
            return entityManager.merge(entity);
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update lesson due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        try {
            return entityManager.createQuery("from " + Lesson.class.getName()).getResultList();
        } catch (DataAccessException e) {
            logger.error("Unable to find all lessons due " + e.getMessage());
            throw new SQLException("Unable to find all lessons due " + e.getMessage(), e);
        }
    }

    @Override
    public Lesson findById(Long id) throws SQLException {
        try {
            return entityManager.find(Lesson.class, id);
        } catch (DataAccessException e) {
            logger.error("Unable to find lesson by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find lesson by id due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try {
            entityManager.createQuery("delete from Lesson where id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (DataAccessException e) {
            logger.error("Unable to delete lesson with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete lesson due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> getLessonsForDay(int day) throws SQLException {
        try {
            return entityManager.createQuery("select l from Lesson l where l.dayOfWeek=:day")
                    .setParameter("day", day)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Unable to find lessons by day {} due " + e.getMessage(), day);
            throw new SQLException("Unable to find lessons by day due " + e.getMessage(), e);
        }
    }
}
