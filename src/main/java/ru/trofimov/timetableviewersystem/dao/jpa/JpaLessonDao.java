package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public Lesson save(Lesson entity) throws SQLException {
        return null;
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        return null;
    }

    @Override
    public Lesson findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public List<Lesson> getLessonsForDay(int day) throws SQLException {
        return null;
    }
}
