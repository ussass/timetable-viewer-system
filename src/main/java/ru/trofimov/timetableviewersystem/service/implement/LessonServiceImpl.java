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
import java.util.stream.StreamSupport;

@Service
public class LessonServiceImpl implements LessonService {

    private static final Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonCrudDao) {
        this.lessonDao = lessonCrudDao;
    }

    @Override
    @Transactional
    public Lesson save(Lesson entity) {
        return lessonDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> findAll() {
        return StreamSupport.stream(lessonDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Lesson findById(Long id) {
        return lessonDao.findById(id).get();
    }

    @Override
    @Transactional
    public Lesson update(Lesson entity) {
        return lessonDao.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        lessonDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Lesson> saveAll(List<Lesson> lessons){
        return lessons.stream().map(lessonDao::save).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsForDay(int day) throws SQLException {
        return lessonDao.findByDayOfWeek(day);
    }

    @Override
    @Transactional
    public void deleteByDay(int day) throws SQLException {
        lessonDao.deleteByDayOfWeek(day);
    }
}
