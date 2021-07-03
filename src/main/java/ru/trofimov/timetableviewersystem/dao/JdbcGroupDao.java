package ru.trofimov.timetableviewersystem.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.trofimov.timetableviewersystem.dao.mapper.GroupMapper;
import ru.trofimov.timetableviewersystem.model.Group;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcGroupDao extends AbstractDao<Group> implements GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Group create(Group entity) throws SQLException {
        String sql = "INSERT INTO groups(group_name) VALUES (?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int updatedRows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql);
            ps.setString(1, entity.getGroupName());
            return ps;
        }, keyHolder);

        if (updatedRows == 1) {
            Group teacher = new Group(entity.getGroupName());
            teacher.setId(keyHolder.getKey().longValue());
            return teacher;
        }
        throw new SQLException("Unable to insert entity");
    }

    @Override
    public Group update(Group entity) throws SQLException {
        String sql = "UPDATE groups SET group_name = ? WHERE group_id = ?";
        int update = jdbcTemplate.update(sql, entity.getGroupName(), entity.getId());
        if (update == 1){
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
        if (delete == 0){
            throw new SQLException("Unable to delete entity");
        }

    }
}
