package ru.trofimov.timetableviewersystem.service.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trofimov.timetableviewersystem.dao.UserDao;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.User;
import ru.trofimov.timetableviewersystem.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userCrudDao, PasswordEncoder passwordEncoder) {
        this.userDao = userCrudDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User entity) {
        entity.setRolesToStringRoles();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        User user = userDao.save(entity);
        user.addRolesFromString();
        logger.info("saved new user with id={}", user.getId());
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        logger.info("Got all users");
        List<User> users = StreamSupport.stream(userDao.findAll().spliterator(), false)
                .collect(Collectors.toList());
        users.forEach(User::addRolesFromString);
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        logger.info("Got user by id = {}", id);
        User user = userDao.findById(id).get();
        user.addRolesFromString();
        return user;
    }

    @Override
    @Transactional
    public User update(User entity) {
        entity.setRolesToStringRoles();
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        logger.info("updated user with id = {}", entity.getId());
        User user = userDao.save(entity);
        user.addRolesFromString();
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        logger.info("deleted user with id = {}", id);
        userDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        logger.info("Got user by login = {}", login);
        User user = userDao.findByLogin(login);
        if (user != null){
            user.addRolesFromString();
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAllStudent() {
        logger.info("Got all student");
        System.out.println("Students:");
        return userDao.findByStringRolesLike("%STUDENT%").stream().map(Student::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAllTeacher() {
        logger.info("Got all teacher");
        return userDao.findByStringRolesLike("%TEACHER%").stream().map(Teacher::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllByGroup(Long id) {
        logger.info("Got all users by Group");
        List<User> users = StreamSupport.stream(userDao.findAllByGroupId(id).spliterator(), false)
                .collect(Collectors.toList());
        users.forEach(User::addRolesFromString);
        return users;
    }
}
