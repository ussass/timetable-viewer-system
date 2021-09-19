package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.model.Classroom;
import ru.trofimov.timetableviewersystem.service.ClassroomService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private static final Logger logger = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    private final ClassroomDao classroomDao;

    public ClassroomServiceImpl(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @Override
    @Transactional
    public Classroom save(Classroom entity) {
        Classroom classroom = classroomDao.save(entity);
        logger.info("saved new classroom with id={}", classroom.getId());
        return classroom;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classroom> findAll() {
        logger.info("Got all classrooms");
        return StreamSupport.stream(classroomDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Classroom findById(Long id) {
        logger.info("Got classroom by id = {}", id);
        return classroomDao.findById(id).get();
    }

    @Override
    @Transactional
    public Classroom update(Classroom entity) {
        logger.info("updated classroom with id = {}", entity.getId());
        return classroomDao.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("deleted classroom with id = {}", id);
        classroomDao.deleteById(id);
    }
}
