package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Classroom;
import ru.trofimov.timetableviewersystem.service.ClassroomService;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomDao classroomDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public ClassroomServiceImpl(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @Override
    @Transactional
    public Classroom save(Classroom entity) throws SQLException {
        Classroom classroom = classroomDao.save(entity);
        logger.info("saved new classroom with id={}", classroom.getId());
        return classroom;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classroom> findAll() {
        logger.info("Got all classrooms");
        return classroomDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Classroom findById(Long id) throws SQLException {
        logger.info("Got classroom by id = {}", id);
        return classroomDao.findById(id);
    }

    @Override
    @Transactional
    public Classroom update(Classroom entity) throws SQLException {
        logger.info("updated classroom with id = {}", entity.getId());
        return classroomDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted classroom with id = {}", id);
        classroomDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> getClassroomTimetable(long classroomId, long startDate, long finishDate) {
        logger.info("Got schedule for classroom with id = {} for dates {} - {}", classroomId, startDate, finishDate);
        return classroomDao.getClassroomTimetable(classroomId, startDate, finishDate);
    }
}
