package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Student;

import java.util.List;

public interface StudentDao {
    void add(Student student);
    public List<Student> findAll();
    public Student findStudentById(int id);
}
