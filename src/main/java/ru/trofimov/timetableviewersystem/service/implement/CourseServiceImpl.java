package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.repositories.CourseRepository;
import ru.trofimov.timetableviewersystem.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public Course save(Course entity) {
        Course course = courseRepository.save(entity);
        logger.info("saved new course with id={}", course.getId());
        return course;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        logger.info("Got all courses");
        return StreamSupport.stream(courseRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long id) {
        logger.info("Got course by id = {}", id);
        return courseRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Course update(Course entity) {
        logger.info("updated course with id = {}", entity.getId());
        return courseRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("deleted course with id = {}", id);
        courseRepository.deleteById(id);
    }
}
