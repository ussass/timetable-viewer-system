package ru.trofimov.timetableviewersystem.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.dao.jdbc.JdbcClassroomDao;
import ru.trofimov.timetableviewersystem.model.Classroom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JpaClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {

    private static final Logger logger = LoggerFactory.getLogger(JdbcClassroomDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Classroom create(Classroom entity) throws SQLException {
        return null;
    }

    @Override
    public Classroom update(Classroom entity) throws SQLException {
        return null;
    }

    @Override
    public List<Classroom> findAll() throws SQLException {
        return null;
    }

    @Override
    public Classroom findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public List<Classroom> findAllTest() {
        return null;
    }
}
