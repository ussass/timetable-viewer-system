package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.UserCourseDao;
import ru.trofimov.timetableviewersystem.model.UserCourse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Repository
public class JpaUserCourseDao implements UserCourseDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaUserCourseDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserCourse save(UserCourse entity) throws SQLException {
        return null;
    }

    @Override
    public UserCourse update(UserCourse entity) throws SQLException {
        return null;
    }

    @Override
    public void deleteByUserId(Long id) throws SQLException {

    }

    @Override
    public void deleteByCourseId(Long id) throws SQLException {

    }
}
