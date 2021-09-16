package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public User save(User entity) throws SQLException {
        entity.setRolesToStringRoles();
        User user = userDao.save(entity);
        user.addRolesFromString();
        logger.info("saved new user with id={}", user.getId());
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() throws SQLException {
        logger.info("Got all users");
        List<User> users = userDao.findAll();
        users.forEach(User::addRolesFromString);
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) throws SQLException {
        logger.info("Got user by id = {}", id);
        User user = userDao.findById(id);
        user.addRolesFromString();
        return user;
    }

    @Override
    @Transactional
    public User update(User entity) throws SQLException {
        entity.setRolesToStringRoles();
        logger.info("updated user with id = {}", entity.getId());
        User user = userDao.update(entity);
        user.addRolesFromString();
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) throws SQLException {
        logger.info("deleted user with id = {}", id);
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) throws SQLException {
        logger.info("Got user by login = {}", login);
        User user = userDao.findByLogin(login);
        if (user != null){
            user.addRolesFromString();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudent() throws SQLException {
        logger.info("Got all student");
        return userDao.findAllStudent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAllTeacher() throws SQLException {
        logger.info("Got all teacher");
        return userDao.findAllTeacher();
    }

    @Override
    @Transactional(readOnly = true)
    public Student findStudentById(Long id) throws SQLException {
        logger.info("Got student by id = {}", id);
        return userDao.findStudentById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findTeacherById(Long id) throws SQLException {
        logger.info("Got teacher by id = {}", id);
        return userDao.findTeacherById(id);
    }
}
