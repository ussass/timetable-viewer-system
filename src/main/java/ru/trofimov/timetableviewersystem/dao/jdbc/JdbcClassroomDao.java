package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.dao.mapper.ClassesMapper;
import ru.trofimov.timetableviewersystem.dao.mapper.ClassroomMapper;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Classroom;
import ru.trofimov.timetableviewersystem.service.implement.ClassesServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public JdbcClassroomDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Classroom create(Classroom entity) throws SQLException {
        String sql = "INSERT INTO classrooms(classroom_number) VALUES (?) RETURNING classroom_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, entity.getNumber());
                return ps;
            }, keyHolder);

            Classroom classroom = new Classroom(entity.getNumber());
            classroom.setId(keyHolder.getKey().longValue());
            return classroom;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into classrooms {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into classrooms due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Classroom> findAll() throws SQLException {
        String sql = "SELECT * from classrooms";

        try {
            return jdbcTemplate.query(sql, new ClassroomMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all classrooms due " + e.getMessage());
            throw new SQLException("Unable to find all classrooms due " + e.getMessage(), e);
        }
    }

    @Override
    public Classroom findById(Long id) throws SQLException {
        String sql = "SELECT * FROM classrooms WHERE classroom_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ClassroomMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find classroom by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find classroom by id due " + e.getMessage(), e);
        }
    }

    @Override
    public Classroom update(Classroom entity) throws SQLException {
        String sql = "UPDATE classrooms SET classroom_number = ? WHERE classroom_id = ?";

        try {
            jdbcTemplate.update(sql, entity.getNumber(), entity.getId());
            Classroom classroom = new Classroom(entity.getNumber());
            classroom.setId(entity.getId());
            return classroom;
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update classroom due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM classrooms WHERE classroom_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete classroom with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete classroom due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Classes> getClassroomTimetable(long classroomId, long startDate, long finishDate) throws SQLException {
        String sql = "SELECT * FROM classes WHERE classroom_id = ? AND classes_date BETWEEN ? AND ?";

        try {
            return jdbcTemplate.query(sql, new ClassesMapper(), classroomId, startDate, finishDate);

        } catch (DataAccessException e) {
            logger.error("Unable to get classroom's timetable with classroomId = {}, startDate = {}, finishDate = {} due "
                    + e.getMessage(), classroomId, startDate, finishDate, e);
            throw new SQLException("Unable to get classroom's timetable due " + e.getMessage(), e);
        }
    }
}
