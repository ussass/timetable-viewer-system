package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.UserDao;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User entity) throws SQLException {
        User user = userDao.save(entity);
        logger.info("saved new user with id={}", user.getId());
        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        logger.info("Got all users");
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) throws SQLException {
        logger.info("Got user by id = {}", id);
        return userDao.findById(id);
    }

    @Override
    public User update(User entity) throws SQLException {
        logger.info("updated user with id = {}", entity.getId());
        return userDao.update(entity);
    }

    @Override
    public void delete(Long id) throws SQLException {
        logger.info("deleted user with id = {}", id);
        userDao.delete(id);
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        logger.info("Got user by login = {}", login);
        return userDao.findByLogin(login);
    }

    @Override
    public List<User> findAllStudent() throws SQLException {
        logger.info("Got all student");
        return userDao.findAllStudent();
    }

    @Override
    public List<User> findAllTeacher() throws SQLException {
        logger.info("Got all teacher");
        return userDao.findAllTeacher();
    }
}
