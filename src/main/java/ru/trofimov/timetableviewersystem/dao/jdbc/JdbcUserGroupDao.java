package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.UserGroupDao;
import ru.trofimov.timetableviewersystem.model.Course;
import ru.trofimov.timetableviewersystem.model.UserGroup;
import ru.trofimov.timetableviewersystem.service.implement.ClassesServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class JdbcUserGroupDao implements UserGroupDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public JdbcUserGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserGroup save(UserGroup entity) throws SQLException {
        String sql = "INSERT INTO users_groups VALUES (?,?)";

        try {
            jdbcTemplate.update(
                    sql,
                    entity.getUserId(),
                    entity.getGroupId()
            );

            return new UserGroup(entity.getUserId(), entity.getGroupId());
        } catch (DataAccessException e) {
            logger.error("Unable to insert into {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into user_group due " + e.getMessage(), e);
        }
    }

    @Override
    public UserGroup update(UserGroup entity) throws SQLException {
        String sql = "UPDATE users_groups SET group_id = ? WHERE user_id = ?";


        try {
            jdbcTemplate.update(
                    sql,
                    entity.getGroupId(),
                    entity.getUserId()
            );

            return new UserGroup(entity.getUserId(), entity.getGroupId());
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update user_group due " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {
        String sql = "DELETE FROM users_groups WHERE user_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete user_group with user_id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete user_group due " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByGroupId(Long id) throws SQLException {
        String sql = "DELETE FROM users_groups WHERE group_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete user_group with group_id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete user_group due " + e.getMessage(), e);
        }
    }
}
