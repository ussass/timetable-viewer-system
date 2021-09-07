package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.GroupDao;
import ru.trofimov.timetableviewersystem.dao.mapper.GroupMapper;
import ru.trofimov.timetableviewersystem.model.Group;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcGroupDao extends AbstractDao<Group> implements GroupDao {
    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcGroupDao.class);

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Group create(Group entity) throws SQLException {
        String sql = "INSERT INTO groups(group_name) VALUES (?) RETURNING group_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entity.getGroupName());
                return ps;
            }, keyHolder);

            Group group = new Group(entity.getGroupName());
            group.setId(keyHolder.getKey().longValue());
            return group;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into groups {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into groups due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Group> findAll() throws SQLException {
        String sql = "SELECT * from groups";

        try {
            return jdbcTemplate.query(sql, new GroupMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all groups due " + e.getMessage());
            throw new SQLException("Unable to find all groups due " + e.getMessage(), e);
        }
    }

    @Override
    public Group findById(Long id) throws SQLException {
        String sql = "SELECT * FROM groups WHERE group_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new GroupMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find group by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find group by id due " + e.getMessage(), e);
        }
    }

    @Override
    public Group update(Group entity) throws SQLException {
        String sql = "UPDATE groups SET group_name = ? WHERE group_id = ?";

        try {
            jdbcTemplate.update(sql, entity.getGroupName(), entity.getId());
            Group group = new Group(entity.getGroupName());
            group.setId(entity.getId());
            return group;
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update group due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM groups WHERE group_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete group with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete group due " + e.getMessage(), e);
        }
    }
}

