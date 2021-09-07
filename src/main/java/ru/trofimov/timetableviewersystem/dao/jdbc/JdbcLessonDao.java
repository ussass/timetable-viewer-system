package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.LessonDao;
import ru.trofimov.timetableviewersystem.dao.mapper.LessonMapper;
import ru.trofimov.timetableviewersystem.model.Lesson;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

@Repository
public class JdbcLessonDao implements LessonDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcLessonDao.class);

    public JdbcLessonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Lesson save(Lesson entity) throws SQLException {
        String sql = "INSERT INTO lessons (course_id, user_id, group_id, classroom_id, lesson_slot_id, day_of_week) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING lesson_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if (entity.getCourseId() == 0) {
                    ps.setNull(1, Types.BIGINT);
                } else {
                    ps.setLong(1, entity.getCourseId());
                }
                if (entity.getTeacherId() == 0) {
                    ps.setNull(2, Types.BIGINT);
                } else {
                    ps.setLong(2, entity.getTeacherId());
                }
                if (entity.getGroupId() == 0) {
                    ps.setNull(3, Types.BIGINT);
                } else {
                    ps.setLong(3, entity.getGroupId());
                }
                if (entity.getClassroomId() == 0) {
                    ps.setNull(4, Types.BIGINT);
                } else {
                    ps.setLong(4, entity.getClassroomId());
                }
                if (entity.getLessonSlotId() == 0) {
                    ps.setNull(5, Types.BIGINT);
                } else {
                    ps.setLong(5, entity.getLessonSlotId());
                }
                ps.setInt(6, entity.getDayOfWeek());
                return ps;
            }, keyHolder);

            Lesson lesson = new Lesson(entity.getCourseId(), entity.getTeacherId(), entity.getGroupId(),
                    entity.getClassroomId(), entity.getLessonSlotId(), entity.getDayOfWeek());
            lesson.setId(entity.getId());
            return lesson;

        } catch (DataAccessException e) {
            logger.error("Unable to insert into lessons {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into lessons due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> findAll() throws SQLException {
        String sql = "SELECT * from lessons";

        try {
            return jdbcTemplate.query(sql, new LessonMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all lessons due " + e.getMessage());
            throw new SQLException("Unable to find all lessons due " + e.getMessage(), e);
        }
    }

    @Override
    public Lesson findById(Long id) throws SQLException {
        String sql = "SELECT * FROM lessons WHERE lesson_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new LessonMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find lesson by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find lesson by id due " + e.getMessage(), e);
        }
    }

    @Override
    public Lesson update(Lesson entity) throws SQLException {
        String sql = "UPDATE lessons SET course_id = ?, user_id = ?, group_id = ?, classroom_id = ?, " +
                "lesson_slot_id = ?, day_of_week = ? WHERE lesson_id = ?";

        try {
            jdbcTemplate.update(
                    sql,
                    entity.getCourseId() == 0 ? null : entity.getCourseId(),
                    entity.getTeacherId() == 0 ? null : entity.getTeacherId(),
                    entity.getGroupId() == 0 ? null : entity.getGroupId(),
                    entity.getClassroomId(),
                    entity.getLessonSlotId(),
                    entity.getDayOfWeek(),
                    entity.getId()
            );
            return new Lesson(entity.getCourseId(), entity.getTeacherId(), entity.getGroupId(),
                    entity.getClassroomId(), entity.getLessonSlotId(), entity.getDayOfWeek());
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update lesson due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM lessons WHERE lesson_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete lesson with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete lesson due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Lesson> getLessonsForDay(int day) throws SQLException {
        String sql = "SELECT * from lessons WHERE day_of_week = ?";

        try {
            return jdbcTemplate.query(sql, new Object[]{day}, new LessonMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find lesson by day {} due " + e.getMessage(), day);
            throw new SQLException("Unable to find lesson by day due " + e.getMessage(), e);
        }
    }
}
