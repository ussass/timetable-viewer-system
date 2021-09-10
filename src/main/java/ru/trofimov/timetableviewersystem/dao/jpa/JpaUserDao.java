package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.UserDao;
import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.model.User;

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
    public User save(User entity) throws SQLException {
        return null;
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public User update(User entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

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
    public List<Student> findAllStudent() throws SQLException {
        return null;
    }

    @Override
    public List<Teacher> findAllTeacher() throws SQLException {
        return null;
    }

    @Override
    public Student findStudentById(Long id) throws SQLException {
        return null;
    }

    @Override
    public Teacher findTeacherById(Long id) throws SQLException {
        return null;
    }
}
