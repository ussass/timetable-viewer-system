package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.StudentService;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Student save(Student entity) throws SQLException {
        return studentDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) throws SQLException {
        return studentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Student update(Student entity) throws SQLException {
        return studentDao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) throws SQLException {
        studentDao.delete(id);
    }
}
