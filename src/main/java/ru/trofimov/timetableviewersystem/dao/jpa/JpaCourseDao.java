package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
        try {
            entityManager.persist(entity);
            return entity;
        } catch (DataAccessException e) {
            logger.error("Unable to insert into courses {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to insert into courses due " + e.getMessage(), e);
        }
    }

    @Override
    public Course update(Course entity) throws SQLException {
        try {
            return entityManager.merge(entity);
        } catch (DataAccessException e) {
            logger.error("Unable to update {} due " + e.getMessage(), entity);
            throw new SQLException("Unable to update course due " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> findAll() throws SQLException {
        try {
            return entityManager.createQuery("from " + Course.class.getName()).getResultList();
        } catch (DataAccessException e) {
            logger.error("Unable to find all courses due " + e.getMessage());
            throw new SQLException("Unable to find all courses {} due " + e.getMessage(), e);
        }
    }

    @Override
    public Course findById(Long id) throws SQLException {
        try {
            return entityManager.find(Course.class, id);
        } catch (DataAccessException e) {
            logger.error("Unable to find course by id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to find course by id due " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try {
            entityManager.createQuery("delete from Course where id=:id")
                    .setParameter("id", id)
                    .executeUpdate();
        } catch (DataAccessException e) {
            logger.error("Unable to delete course with id {} due " + e.getMessage(), id);
            throw new SQLException("Unable to delete course due " + e.getMessage(), e);
        }
    }
}
