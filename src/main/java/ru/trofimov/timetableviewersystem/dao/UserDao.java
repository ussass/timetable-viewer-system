package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.Student;
import ru.trofimov.timetableviewersystem.model.Teacher;
import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends Dao<User, Long> {
    User findByLogin(String login) throws SQLException;

    List<User> findAllStudent() throws SQLException;

    List<User> findAllTeacher() throws SQLException;

    Student findStudentById(Long id) throws SQLException;

    Teacher findTeacherById(Long id) throws SQLException;
}
