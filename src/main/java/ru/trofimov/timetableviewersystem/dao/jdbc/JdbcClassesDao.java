package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.ClassesDao;
import ru.trofimov.timetableviewersystem.dao.mapper.ClassesMapper;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.service.implement.ClassesServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcClassesDao extends AbstractDao<Classes> implements ClassesDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public JdbcClassesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Classes create(Classes entity) throws SQLException {
        String sql = "INSERT INTO classes (course_id, teacher_id, group_id, classroom_id, lesson_slot_id, " +
                "classes_date) VALUES (?, ?, ?, ?, ?, ?) RETURNING classes_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, entity.getCourseId());
                ps.setLong(2, entity.getTeacherId());
                ps.setLong(3, entity.getGroupId());
                ps.setLong(4, entity.getClassroomId());
                ps.setLong(5, entity.getLessonSlotId());
                ps.setLong(6, entity.getDate().getTime());
                return ps;
            }, keyHolder);

            Classes classes = new Classes(entity.getCourseId(), entity.getTeacherId(), entity.getGroupId(),
                    entity.getClassroomId(), entity.getLessonSlotId(), entity.getDate());
            classes.setId(keyHolder.getKey().longValue());
            return classes;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into classes {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into classes due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Classes> findAll() throws SQLException {
        String sql = "SELECT * from classes";
        try {
            return jdbcTemplate.query(sql, new ClassesMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all classes due " + e.getMessage());
            throw new SQLException("Unable to find all classes due " + e.getMessage(), e);
        }
    }

    @Override
    public Classes findById(Long id) throws SQLException {
        String sql = "SELECT * FROM classes WHERE classes_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ClassesMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find classes by id {} due" + e.getMessage(), id);
            throw new SQLException("Unable to find by id classes due " + e.getMessage(), e);
        }
    }

    @Override
    public Classes update(Classes entity) throws SQLException {

        String sql = "UPDATE classes SET course_id = ?, teacher_id = ?, group_id = ?," +
                " classroom_id = ?, lesson_slot_id = ?, classes_date = ? WHERE classes_id = ?";
        try {
            jdbcTemplate.update(sql, entity.getCourseId(), entity.getTeacherId(), entity.getGroupId(),
                    entity.getClassroomId(), entity.getLessonSlotId(), entity.getDate(), entity.getId());
            Classes classes = new Classes(entity.getCourseId(), entity.getTeacherId(), entity.getGroupId(),
                    entity.getClassroomId(), entity.getLessonSlotId(), entity.getDate());
            classes.setId(entity.getId());
            return classes;
        } catch (DataAccessException e) {
            logger.error("Unable to update classes {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update classes due " + e.getMessage(), e);
        }

    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM classes WHERE classes_id = ?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete classes with id = {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete classes due " + e.getMessage(), e);
        }
    }
}
