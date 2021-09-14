package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        return entityManager.merge(entity);
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        return entityManager.createQuery("from " + Lesson.class.getName()).getResultList();
    }

    @Override
    public Lesson findById(Long id) throws SQLException {
        return entityManager.find(Lesson.class, id);
    }

    @Override
    public void delete(Long id) throws SQLException {
        entityManager.createQuery("delete from " + Lesson.class.getName() + " where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Lesson> getLessonsForDay(int day) throws SQLException {
        return null;
    }
}
