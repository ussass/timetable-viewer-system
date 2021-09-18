package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.LessonSlotDao;
import ru.trofimov.timetableviewersystem.model.LessonSlot;
import ru.trofimov.timetableviewersystem.service.LessonSlotService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LessonSlotServiceImpl implements LessonSlotService {

    private static final Logger logger = LoggerFactory.getLogger(LessonSlotServiceImpl.class);

    private final LessonSlotDao lessonSlotDao;

    public LessonSlotServiceImpl(LessonSlotDao lessonSlotDao) {
        this.lessonSlotDao = lessonSlotDao;
    }

    @Override
    @Transactional
    public LessonSlot save(LessonSlot entity) throws SQLException {
        LessonSlot lessonSlot = lessonSlotDao.save(entity);
        logger.info("saved new lessonSlot with id={}", lessonSlot.getId());
        return lessonSlot;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonSlot> findAll() throws SQLException {
        logger.info("Got all lessonSlots");
        return StreamSupport.stream(lessonSlotDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LessonSlot findById(Long id) throws SQLException {
        logger.info("Got lessonSlot by id = {}", id);
        return lessonSlotDao.findById(id).get();
    }

    @Override
    @Transactional
    public LessonSlot update(LessonSlot entity) throws SQLException {
        logger.info("updated lessonSlot with id = {}", entity.getId());
        return lessonSlotDao.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted lessonSlot with id = {}", id);
        lessonSlotDao.delete(new LessonSlot(id));
    }
}
