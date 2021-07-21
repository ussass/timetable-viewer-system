package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.TeacherDao;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher save(Teacher entity) throws SQLException {
        return teacherDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(Long id) throws SQLException {
        return teacherDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher update(Teacher entity) throws SQLException {
        return teacherDao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) throws SQLException {
        teacherDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classes> getTeacherTimetable(long teacherId, long startDate, long finishDate) {
        return teacherDao.getTeacherTimetable(teacherId, startDate, finishDate);
    }
}
