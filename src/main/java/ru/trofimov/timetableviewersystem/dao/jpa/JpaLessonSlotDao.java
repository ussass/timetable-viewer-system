package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.LessonSlotDao;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaLessonSlotDao extends AbstractDao<LessonSlot> implements LessonSlotDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaLessonSlotDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LessonSlot create(LessonSlot entity) throws SQLException {
        return null;
    }

    @Override
    public LessonSlot update(LessonSlot entity) throws SQLException {
        return null;
    }

    @Override
    public List<LessonSlot> findAll() throws SQLException {
        return null;
    }

    @Override
    public LessonSlot findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
