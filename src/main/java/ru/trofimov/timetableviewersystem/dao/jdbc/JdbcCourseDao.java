package ru.trofimov.timetableviewersystem.dao.jdbc;

import org.springframework.stereotype.Component;
import ru.trofimov.timetableviewersystem.dao.AbstractDao;
import ru.trofimov.timetableviewersystem.dao.CourseDao;
import ru.trofimov.timetableviewersystem.model.Course;

import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcCourseDao extends AbstractDao<Course> implements CourseDao {
    @Override
    public Course create(Course entity) throws SQLException {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public Course update(Course entity) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
