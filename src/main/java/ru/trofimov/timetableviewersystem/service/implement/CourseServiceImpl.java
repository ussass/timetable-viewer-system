package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.service.CourseService;

import java.sql.SQLException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    @Transactional
    public Course save(Course entity) throws SQLException {
        Course course = courseDao.save(entity);
        logger.info("saved new course with id={}", course.getId());
        return course;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() throws SQLException {
        logger.info("Got all courses");
        return courseDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long id) throws SQLException {
        logger.info("Got course by id = {}", id);
        return courseDao.findById(id);
    }

    @Override
    @Transactional
    public Course update(Course entity) throws SQLException {
        logger.info("updated course with id = {}", entity.getId());
        return courseDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted course with id = {}", id);
        courseDao.delete(id);
    }
}
