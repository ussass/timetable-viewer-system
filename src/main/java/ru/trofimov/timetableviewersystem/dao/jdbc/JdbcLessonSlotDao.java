package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.LessonSlotDao;
import ru.trofimov.timetableviewersystem.dao.mapper.LessonSlotMapper;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

// @Repository
public class JdbcLessonSlotDao extends AbstractDao<LessonSlot> implements LessonSlotDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcLessonSlotDao.class);

    public JdbcLessonSlotDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LessonSlot create(LessonSlot entity) throws SQLException {
        String sql = "INSERT INTO lesson_slot(lesson_slot_number, min_start) VALUES (?,?) RETURNING lesson_slot_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, entity.getNumber());
                ps.setInt(2, entity.getMinStart());
                return ps;
            }, keyHolder);

            LessonSlot lessonSlot = new LessonSlot(entity.getNumber(), entity.getMinStart());
            lessonSlot.setId(keyHolder.getKey().longValue());
            return lessonSlot;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into lesson_slot {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into lesson_slot due " + e.getMessage(), e);
        }
    }

    @Override
    public List<LessonSlot> findAll() throws SQLException {
        String sql = "SELECT * from lesson_slot";

        try {
            return jdbcTemplate.query(sql, new LessonSlotMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all lesson slots due " + e.getMessage());
            throw new SQLException("Unable to find all lesson slots due " + e.getMessage(), e);
        }
    }

    @Override
    public LessonSlot findById(Long id) throws SQLException {
        String sql = "SELECT * FROM lesson_slot WHERE lesson_slot_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new LessonSlotMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find lesson slot by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find lesson slot by id due " + e.getMessage(), e);
        }
    }

    @Override
    public LessonSlot update(LessonSlot entity) throws SQLException {
        String sql = "UPDATE lesson_slot SET lesson_slot_number = ? WHERE lesson_slot_id = ?";

        try {
            jdbcTemplate.update(sql, entity.getNumber(), entity.getId());
            LessonSlot lessonSlot = new LessonSlot(entity.getNumber(), entity.getMinStart());
            lessonSlot.setId(entity.getId());
            return lessonSlot;
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update lesson slot due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM lesson_slot WHERE lesson_slot_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete lesson slot with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete lesson slot due " + e.getMessage(), e);
        }
    }
}
