package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.LessonDao;
import ru.trofimov.timetableviewersystem.model.Lesson;
import ru.trofimov.timetableviewersystem.service.LessonService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    private static final Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    @Transactional
    public Lesson save(Lesson entity) throws SQLException {
        return lessonDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> findAll() throws SQLException {
        return lessonDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Lesson findById(Long id) throws SQLException {
        return lessonDao.findById(id);
    }

    @Override
    @Transactional
    public Lesson update(Lesson entity) throws SQLException {
        return lessonDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        lessonDao.delete(id);
    }

    @Override
    @Transactional
    public List<Lesson> saveAll(List<Lesson> lessons){
        return lessons.stream().map(lesson -> {
            try {
                return lessonDao.save(lesson);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return lesson;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsForDay(int day) throws SQLException {
        return lessonDao.getLessonsForDay(day);
    }

    @Override
    @Transactional
    public void deleteByDay(int day) throws SQLException {
        lessonDao.deleteByDay(day);
    }
}
