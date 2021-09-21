package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.model.Lesson;
import ru.trofimov.timetableviewersystem.repositories.LessonRepository;
import ru.trofimov.timetableviewersystem.service.LessonService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LessonServiceImpl implements LessonService {

    private static final Logger logger = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    @Transactional
    public Lesson save(Lesson entity) {
        return lessonRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> findAll() {
        return StreamSupport.stream(lessonRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Lesson update(Lesson entity) {
        return lessonRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Lesson> saveAll(List<Lesson> lessons){
        return lessons.stream().map(lessonRepository::save).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Lesson> getLessonsForDay(int day) {
        return lessonRepository.findByDayOfWeek(day);
    }

    @Override
    @Transactional
    public void deleteByDay(int day) {
        lessonRepository.deleteByDayOfWeek(day);
    }
}
