package ru.trofimov.timetableviewersystem.service.implement;

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

    public LessonSlotServiceImpl(LessonSlotDao lessonSlotDao) {
        this.lessonSlotDao = lessonSlotDao;
    }

    @Override
    @Transactional
    public LessonSlot save(LessonSlot entity) throws SQLException {
        return lessonSlotDao.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonSlot> findAll() {
        return lessonSlotDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public LessonSlot findById(Long id) throws SQLException {
        return lessonSlotDao.findById(id);
    }

    @Override
    @Transactional
    public LessonSlot update(LessonSlot entity) throws SQLException {
        return lessonSlotDao.update(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        lessonSlotDao.delete(id);
    }
}
