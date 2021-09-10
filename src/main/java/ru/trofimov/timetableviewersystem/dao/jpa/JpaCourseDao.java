package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.model.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaCourseDao extends AbstractDao<Course> implements CourseDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaCourseDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Course create(Course entity) throws SQLException {
        return null;
    }

    @Override
    public Course update(Course entity) throws SQLException {
        return null;
    }

    @Override
    public List<Course> findAll() throws SQLException {
        return null;
    }

    @Override
    public Course findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
