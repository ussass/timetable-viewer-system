package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.dao.mapper.ClassesMapper;
import ru.trofimov.timetableviewersystem.dao.mapper.GroupMapper;
import ru.trofimov.timetableviewersystem.model.Classes;
import ru.trofimov.timetableviewersystem.model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Component
public class JdbcGroupDao extends AbstractDao<Group> implements GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Group create(Group entity) throws SQLException {
        String sql = "INSERT INTO groups(group_name) VALUES (?) RETURNING group_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getGroupName());
            return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            Group group = new Group(entity.getGroupName());
            group.setId(keyHolder.getKey().longValue());
            return group;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public Group update(Group entity) throws SQLException {
        String sql = "UPDATE groups SET group_name = ? WHERE group_id = ?";
        int update = jdbcTemplate.update(sql, entity.getGroupName(), entity.getId());
        if (update == 1) {
            Group group = new Group(entity.getGroupName());
            group.setId(entity.getId());
            return group;
        }
        throw new SQLException("Unable to update entity");
    }

    @Override
    public List<Group> findAll() {
        String sql = "SELECT * from groups";
        return jdbcTemplate.query(sql, new GroupMapper());
    }

    @Override
    public Group findById(Long id) {
        String sql = "SELECT * FROM groups WHERE group_id = ?";
        Group group = null;
        try {
            group = jdbcTemplate.queryForObject(sql, new Object[]{id}, new GroupMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return group;
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM groups WHERE group_id = ?";
        int delete = jdbcTemplate.update(sql, id);
        if (delete == 0) {
            throw new SQLException("Unable to delete entity");
        }
    }

    @Override
    public List<Classes> getGroupTimetable(long groupId, long startDate, long finishDate) {
        String sql = "SELECT * FROM classes WHERE group_id = ? AND classes_date BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new ClassesMapper(), groupId, startDate, finishDate);
    }
}