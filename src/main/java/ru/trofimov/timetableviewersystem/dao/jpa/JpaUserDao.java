package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.UserDao;
import ru.trofimov.timetableviewersystem.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaUserDao implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaUserDao.class);

    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    public JpaUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @SuppressWarnings("UNCHECKED")
    public User findByLogin(String login) throws SQLException {
        try {
            List<User> users = entityManager.createQuery("select u from User u where u.login=:login")
                    .setParameter("login", login)
                    .getResultList();
            return users.size() == 1 ? users.get(0) : null;
        } catch (Exception e) {
            logger.error("Unable to find user by login {} due " + e.getMessage(), login);
            throw new SQLException("Unable to find user by login due " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAllStudent() throws SQLException {
        try {
            return entityManager.createQuery("select u from User u where u.stringRoles LIKE :role")
                    .setParameter("role", "%" + Role.STUDENT.name() + "%")
                    .getResultList();
        } catch (Exception e) {
            logger.error("Unable to find all students due " + e.getMessage());
            throw new SQLException("Unable to find all students due " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAllTeacher() throws SQLException {
        try {
            return entityManager.createQuery("select u from User u where u.stringRoles LIKE :role")
                    .setParameter("role", "%" + Role.TEACHER.name() + "%")
                    .getResultList();
        } catch (Exception e) {
            logger.error("Unable to find all teachers due " + e.getMessage());
            throw new SQLException("Unable to find all teachers due " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAllByGroup(Long id) throws SQLException {
        try {
            return entityManager.createQuery("select u from User u where u.groupId=:groupId")
                    .setParameter("groupId", id)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Unable to find user by group id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find user by group id due " + e.getMessage(), e);
        }
    }
}
