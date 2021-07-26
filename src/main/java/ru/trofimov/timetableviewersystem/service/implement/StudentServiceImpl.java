package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.StudentDao;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.service.StudentService;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public Student save(Student entity) throws SQLException {
        Student student = studentDao.save(entity);
        logger.info("saved new student with id={}", student.getId());
        return student;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() throws SQLException {
        logger.info("Got all students");
        return studentDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) throws SQLException {
        logger.info("Got student by id = {}", id);
        return studentDao.findById(id);
    }

    @Override
    @Transactional
    public Student update(Student entity) throws SQLException {
        logger.info("updated student with id = {}", entity.getId());
        return studentDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted student with id = {}", id);
        studentDao.delete(id);
    }
}
