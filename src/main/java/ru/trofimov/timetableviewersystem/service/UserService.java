package ru.trofimov.timetableviewersystem.service;


import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends Service<User, Long> {
    User findByLogin(String login) throws SQLException;

    List<Student> findAllStudent() throws SQLException;

    List<User> findAllTeacher() throws SQLException;

    Student findStudentById(Long id) throws SQLException;
}
