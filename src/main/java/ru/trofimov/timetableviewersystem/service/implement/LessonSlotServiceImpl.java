package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.model.LessonSlot;
import ru.trofimov.timetableviewersystem.repositories.LessonSlotRepository;
import ru.trofimov.timetableviewersystem.service.LessonSlotService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LessonSlotServiceImpl implements LessonSlotService {

    private static final Logger logger = LoggerFactory.getLogger(LessonSlotServiceImpl.class);

    private final LessonSlotRepository lessonSlotRepository;

    public LessonSlotServiceImpl(LessonSlotRepository lessonSlotRepository) {
        this.lessonSlotRepository = lessonSlotRepository;
    }

    @Override
    @Transactional
    public LessonSlot save(LessonSlot entity) {
        LessonSlot lessonSlot = lessonSlotRepository.save(entity);
        logger.info("saved new lessonSlot with id={}", lessonSlot.getId());
        return lessonSlot;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonSlot> findAll() {
        logger.info("Got all lessonSlots");
        return StreamSupport.stream(lessonSlotRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LessonSlot findById(Long id) {
        logger.info("Got lessonSlot by id = {}", id);
        return lessonSlotRepository.findById(id).get();
    }

    @Override
    @Transactional
    public LessonSlot update(LessonSlot entity) {
        logger.info("updated lessonSlot with id = {}", entity.getId());
        return lessonSlotRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("deleted lessonSlot with id = {}", id);
        lessonSlotRepository.deleteById(id);
    }
}
