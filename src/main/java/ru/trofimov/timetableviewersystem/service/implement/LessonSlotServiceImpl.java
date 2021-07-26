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

@Service
public class LessonSlotServiceImpl implements LessonSlotService {

    private final LessonSlotDao lessonSlotDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

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
    public List<LessonSlot> findAll() {
        logger.info("Got all lessonSlots");
        return lessonSlotDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public LessonSlot findById(Long id) throws SQLException {
        logger.info("Got lessonSlot by id = {}", id);
        return lessonSlotDao.findById(id);
    }

    @Override
    @Transactional
    public LessonSlot update(LessonSlot entity) throws SQLException {
        logger.info("updated lessonSlot with id = {}", entity.getId());
        return lessonSlotDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted lessonSlot with id = {}", id);
        lessonSlotDao.delete(id);
    }
}
