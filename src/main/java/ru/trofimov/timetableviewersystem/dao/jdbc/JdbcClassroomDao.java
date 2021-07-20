package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.ClassroomDao;
import ru.trofimov.timetableviewersystem.model.Classroom;

import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcClassroomDao extends AbstractDao<Classroom> implements ClassroomDao {
    @Override
    public Classroom create(Classroom entity) throws SQLException {
        return null;
    }

    @Override
    public List<Classroom> findAll() {
        return null;
    }

    @Override
    public Classroom findById(Long id) {
        return null;
    }

    @Override
    public Classroom update(Classroom entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
