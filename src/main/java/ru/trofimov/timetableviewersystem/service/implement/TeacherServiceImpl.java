package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.TeacherDao;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.service.TeacherService;

import java.sql.SQLException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public Teacher save(Teacher entity) throws SQLException {
        return teacherDao.save(entity);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public Teacher findById(Long id) throws SQLException {
        return teacherDao.findById(id);
    }

    @Override
    public Teacher update(Teacher entity) throws SQLException {
        return teacherDao.update(entity);
    }

    @Override
    public void delete(Long id) throws SQLException {
        teacherDao.delete(id);
    }
}
