package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.LessonDao;
import ru.trofimov.timetableviewersystem.model.Lesson;
import ru.trofimov.timetableviewersystem.service.LessonService;

import java.sql.SQLException;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonDao lessonDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

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
}
