package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.trofimov.timetableviewersystem.dao.UserCourseDao;
import ru.trofimov.timetableviewersystem.model.UserCourse;

import java.sql.SQLException;

// @Repository
public class JdbcUserCourseDao implements UserCourseDao {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(JdbcUserCourseDao.class);

    public JdbcUserCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserCourse save(UserCourse entity) throws SQLException {
        String sql = "INSERT INTO users_courses VALUES (?,?)";

        try {
            jdbcTemplate.update(
                    sql,
                    entity.getUserId(),
                    entity.getCourseId()
            );

            return new UserCourse(entity.getUserId(), entity.getCourseId());
        } catch (DataAccessException e) {
            logger.error("Unable to insert into {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into users_courses due " + e.getMessage(), e);
        }
    }

    @Override
    public UserCourse update(UserCourse entity) throws SQLException {
        String sql = "UPDATE users_courses SET course_id = ? WHERE user_id = ?";


        try {
            jdbcTemplate.update(
                    sql,
                    entity.getCourseId(),
                    entity.getUserId()
            );

            return new UserCourse(entity.getUserId(), entity.getCourseId());
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update users_courses due " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {
        String sql = "DELETE FROM users_courses WHERE user_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete users_courses with user_id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete users_courses due " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByCourseId(Long id) throws SQLException {
        String sql = "DELETE FROM users_courses WHERE course_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete users_courses with group_id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete users_courses due " + e.getMessage(), e);
        }

    }
}
