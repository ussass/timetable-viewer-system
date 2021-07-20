package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.LessonSlotDao;
import ru.trofimov.timetableviewersystem.dao.mapper.LessonSlotMapper;
import ru.trofimov.timetableviewersystem.model.LessonSlot;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcLessonSlotDao extends AbstractDao<LessonSlot> implements LessonSlotDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcLessonSlotDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public LessonSlot create(LessonSlot entity) throws SQLException {
        String sql = "INSERT INTO lesson_slot(lesson_slot_number) VALUES (?) RETURNING lesson_slot_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getNumber());
            return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            LessonSlot lessonSlot = new LessonSlot(entity.getNumber());
            lessonSlot.setId(keyHolder.getKey().longValue());
            return lessonSlot;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public List<LessonSlot> findAll() {
        String sql = "SELECT * from lesson_slot";
        return jdbcTemplate.query(sql, new LessonSlotMapper());
    }

    @Override
    public LessonSlot findById(Long id) throws SQLException {
        String sql = "SELECT * FROM lesson_slot WHERE lesson_slot_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new LessonSlotMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        throw new SQLException("Unable to find by id entity");
    }

    @Override
    public LessonSlot update(LessonSlot entity) throws SQLException {
        String sql = "UPDATE lesson_slot SET lesson_slot_number = ? WHERE lesson_slot_id = ?";
        int update = jdbcTemplate.update(sql, entity.getNumber(), entity.getId());
        if (update == 1) {
            LessonSlot lessonSlot = new LessonSlot(entity.getNumber());
            lessonSlot.setId(entity.getId());
            return lessonSlot;
        }
        throw new SQLException("Unable to update entity");
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM lesson_slot WHERE lesson_slot_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 0) {
            throw new SQLException("Unable to delete entity");
        }
    }
}
