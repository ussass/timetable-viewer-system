package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.LessonDao;
import ru.trofimov.timetableviewersystem.model.Lesson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaLessonDao implements LessonDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaLessonDao.class);

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public void deleteByDay(int day) throws SQLException {
        try {
            entityManager.createQuery("delete from Lesson l where l.dayOfWeek=:day")
                    .setParameter("day", day)
                    .executeUpdate();
        } catch (DataAccessException e) {
            logger.error("Unable to delete lesson with day {} due " + e.getMessage(), day);
            throw new SQLException("Unable to delete lesson due " + e.getMessage(), e);
        }
    }
}
