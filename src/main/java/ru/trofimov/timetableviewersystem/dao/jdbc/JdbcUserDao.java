package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.UserDao;
import ru.trofimov.timetableviewersystem.dao.mapper.UserGroupMapper;
import ru.trofimov.timetableviewersystem.dao.mapper.UserMapper;
import ru.trofimov.timetableviewersystem.model.Role;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.implement.ClassesServiceImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class JdbcUserDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User entity) throws SQLException {
        String sql = "INSERT INTO users(first_name, last_name, login, password, roles) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING user_id;";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, entity.getFirstName());
                ps.setString(2, entity.getLastName());
                ps.setString(3, entity.getLogin());
                ps.setString(4, passwordEncoder.encode(entity.getPassword()));
                ps.setString(5, entity.getStringRoles());
                return ps;
            }, keyHolder);

            return new User(entity.getId(), entity.getFirstName(), entity.getLastName(),
                    entity.getLogin(), entity.getPassword(), entity.getRoles());
        } catch (DataAccessException e) {
            logger.error("Unable to insert into users {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into users due " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        String sql = "SELECT * from users";

        try {
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all users due " + e.getMessage());
            throw new SQLException("Unable to find all users due " + e.getMessage(), e);
        }
    }

    @Override
    public User findById(Long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find user by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find user by id due " + e.getMessage(), e);
        }
    }

    @Override
    public User update(User entity) throws SQLException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, roles = ? " +
                "WHERE user_id = ?";

        try {
            jdbcTemplate.update(
                    sql,
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getLogin(),
                    entity.getPassword(),
                    entity.getStringRoles(),
                    entity.getId()
            );
            return new User(entity.getId(), entity.getFirstName(), entity.getLastName(),
                    entity.getLogin(), entity.getPassword(), entity.getRoles());
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update user due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try {
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            logger.error("Unable to delete user with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete user due " + e.getMessage(), e);
        }
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        String sql = "SELECT * FROM users WHERE login = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{login}, new UserMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find user by login {} due " + e.getMessage(), login);
            throw new SQLException("Unable to find user by login due " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAllTeacher() throws SQLException {
        String sql = "SELECT * FROM public.users WHERE roles LIKE '%" + Role.TEACHER.name() + "%'";

        try {
            return jdbcTemplate.query(sql, new UserMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all teacher due " + e.getMessage());
            throw new SQLException("Unable to find all teacher due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> findAllStudent() throws SQLException {
        String sql = "SELECT users.user_id, users.first_name, users.last_name, users.login, users.password, users.roles, groups.group_id, groups.group_name\n" +
                "  FROM users \n" +
                "LEFT OUTER JOIN users_groups\n" +
                "  ON users.user_id = users_groups.user_id \n" +
                "LEFT OUTER JOIN groups\n" +
                "  ON users_groups.group_id = groups.group_id WHERE roles LIKE '%" + Role.STUDENT.name() + "%'";

        try {
            return jdbcTemplate.query(sql, new UserGroupMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find all students due " + e.getMessage());
            throw new SQLException("Unable to find all students due " + e.getMessage(), e);
        }
    }

    @Override
    public Student findStudentById(Long id) throws SQLException {
        String sql = "SELECT users.user_id, users.first_name, users.last_name, users.login, users.password, users.roles, groups.group_id, groups.group_name\n" +
                "  FROM users \n" +
                "LEFT OUTER JOIN users_groups\n" +
                "  ON users.user_id = users_groups.user_id \n" +
                "LEFT OUTER JOIN groups\n" +
                "  ON users_groups.group_id = groups.group_id WHERE users.user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserGroupMapper());
        } catch (DataAccessException e) {
            logger.error("Unable to find student by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find student by id due " + e.getMessage(), e);
        }
    }
}
