package ru.trofimov.timetableviewersystem.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.service.CourseService;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Course save(Course entity) throws SQLException {
        return courseDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long id) throws SQLException {
        return courseDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Course update(Course entity) throws SQLException {
        return courseDao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Long id) throws SQLException {
        courseDao.delete(id);
    }
}
