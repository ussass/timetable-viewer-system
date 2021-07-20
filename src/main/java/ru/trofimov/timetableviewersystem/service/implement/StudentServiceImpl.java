package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.StudentService;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student save(Student entity) throws SQLException {
        return studentDao.save(entity);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findById(Long id) throws SQLException {
        return studentDao.findById(id);
    }

    @Override
    public Student update(Student entity) throws SQLException {
        return studentDao.update(entity);
    }

    @Override
    public void delete(Long id) throws SQLException {
        studentDao.delete(id);
    }
}
