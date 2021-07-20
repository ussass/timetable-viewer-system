package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.dao.LessonSlotDao;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcLessonSlotDao extends AbstractDao<LessonSlot> implements LessonSlotDao {
    @Override
    public LessonSlot create(LessonSlot entity) throws SQLException {
        return null;
    }

    @Override
    public List<LessonSlot> findAll() {
        return null;
    }

    @Override
    public LessonSlot findById(Long id) {
        return null;
    }

    @Override
    public LessonSlot update(LessonSlot entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
