package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.model.Classroom;
import ru.trofimov.timetableviewersystem.repositories.ClassroomRepository;
import ru.trofimov.timetableviewersystem.service.ClassroomService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    private static final Logger logger = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    private final ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    @Transactional
    public Classroom save(Classroom entity) {
        Classroom classroom = classroomRepository.save(entity);
        logger.info("saved new classroom with id={}", classroom.getId());
        return classroom;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Classroom> findAll() {
        logger.info("Got all classrooms");
        return StreamSupport.stream(classroomRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Classroom findById(Long id) {
        logger.info("Got classroom by id = {}", id);
        return classroomRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Classroom update(Classroom entity) {
        logger.info("updated classroom with id = {}", entity.getId());
        return classroomRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("deleted classroom with id = {}", id);
        classroomRepository.deleteById(id);
    }
}
