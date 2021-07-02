package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;

public class JdbcStudentDao extends AbstractDao<Student> implements StudentDao {
    @Override
    public Student create(Student entity) {
        return null;
    }

    @Override
    public Student update(Student entity) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
