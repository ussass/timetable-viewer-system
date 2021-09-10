package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.trofimov.timetableviewersystem.dao.UserDao;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

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
    public List<Student> findAllStudent() throws SQLException {
        logger.info("Got all student");
        return userDao.findAllStudent();
    }

    @Override
    public List<Teacher> findAllTeacher() throws SQLException {
        logger.info("Got all teacher");
        return userDao.findAllTeacher();
    }

    @Override
    public Student findStudentById(Long id) throws SQLException {
        logger.info("Got student by id = {}", id);
        return userDao.findStudentById(id);
    }

    @Override
    public Teacher findTeacherById(Long id) throws SQLException {
        logger.info("Got teacher by id = {}", id);
        return userDao.findTeacherById(id);
    }
}
