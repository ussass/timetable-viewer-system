package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.LessonDao;
import ru.trofimov.timetableviewersystem.model.Lesson;
import ru.trofimov.timetableviewersystem.service.implement.ClassesServiceImpl;

import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcLessonDao implements LessonDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public JdbcLessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Lesson save(Lesson entity) throws SQLException {
        return null;
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        return null;
    }

    @Override
    public Lesson findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
