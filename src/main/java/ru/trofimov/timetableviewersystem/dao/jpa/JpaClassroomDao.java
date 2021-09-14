package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.model.Classroom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {

    private static final Logger logger = LoggerFactory.getLogger(JpaClassroomDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Classroom create(Classroom entity) throws SQLException {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Classroom update(Classroom entity) throws SQLException {
            return entityManager.merge(entity);
    }

    @Override
    public List<Classroom> findAll() throws SQLException {
        return entityManager.createQuery("from " + Classroom.class.getName()).getResultList();
    }

    @Override
    public Classroom findById(Long id) throws SQLException {
        return entityManager.find(Classroom.class, id);
    }

    @Override
    public void delete(Long id) throws SQLException {
        entityManager.createQuery("delete from " + Classroom.class.getName() + " where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
