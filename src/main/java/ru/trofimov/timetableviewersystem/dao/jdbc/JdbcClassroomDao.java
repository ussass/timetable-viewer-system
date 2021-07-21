package ru.trofimov.timetableviewersystem.dao.jdbc;

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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcClassroomDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Classroom create(Classroom entity) throws SQLException {
        String sql = "INSERT INTO classrooms(classroom_number) VALUES (?) RETURNING classroom_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getNumber());
            return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            Classroom classroom = new Classroom(entity.getNumber());
            classroom.setId(keyHolder.getKey().longValue());
            return classroom;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public List<Classroom> findAll() {
        String sql = "SELECT * from classrooms";
        return jdbcTemplate.query(sql, new ClassroomMapper());
    }

    @Override
    public Classroom findById(Long id) throws SQLException {
        String sql = "SELECT * FROM classrooms WHERE classroom_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ClassroomMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        throw new SQLException("Unable to find by id entity");
    }

    @Override
    public Classroom update(Classroom entity) throws SQLException {
        String sql = "UPDATE classrooms SET classroom_number = ? WHERE classroom_id = ?";
        int update = jdbcTemplate.update(sql, entity.getNumber(), entity.getId());
        if (update == 1) {
            Classroom classroom = new Classroom(entity.getNumber());
            classroom.setId(entity.getId());
            return classroom;
        }
        throw new SQLException("Unable to update entity");
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM classrooms WHERE classroom_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 0) {
            throw new SQLException("Unable to delete entity");
        }
    }

    @Override
    public List<Classes> getClassroomTimetable(long classroomId, long startDate, long finishDate) {
        String sql = "SELECT * FROM classes WHERE classroom_id = ? AND classes_date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new ClassesMapper(), classroomId, startDate, finishDate);

    }
}
