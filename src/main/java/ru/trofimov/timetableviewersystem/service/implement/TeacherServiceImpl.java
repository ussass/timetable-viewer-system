package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.TeacherDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    @Transactional
    public Teacher save(Teacher entity) throws SQLException {
        Teacher teacher = teacherDao.save(entity);
        logger.info("saved new teacher with id={}", teacher.getId());
        return teacher;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        logger.info("Got all teachers");
        return teacherDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(Long id) throws SQLException {
        logger.info("Got teacher by id = {}", id);
        return teacherDao.findById(id);
    }

    @Override
    @Transactional
    public Teacher update(Teacher entity) throws SQLException {
        logger.info("updated teacher with id = {}", entity.getId());
        return teacherDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted teacher with id = {}", id);
        teacherDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> getTeacherTimetable(long teacherId, long startDate, long finishDate) {
        logger.info("Got schedule for teacher with id = {} for dates {} - {}", teacherId, startDate, finishDate);
        return teacherDao.getTeacherTimetable(teacherId, startDate, finishDate);
    }
}
