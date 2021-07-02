package ru.trofimov.timetableviewersystem.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.trofimov.timetableviewersystem.dao.mapper.TeacherMapper;
import ru.trofimov.timetableviewersystem.model.Teacher;

import java.util.List;

public class JdbcTeacherDao extends AbstractDao<Teacher> implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTeacherDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher create(Teacher entity) {
        return null;
    }

    @Override
    public Teacher update(Teacher entity) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT * from students";
        return jdbcTemplate.query(sql, new TeacherMapper());
    }

    @Override
    public Teacher findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
