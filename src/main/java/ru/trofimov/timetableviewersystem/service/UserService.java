package ru.trofimov.timetableviewersystem.service;


import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends Service<User, Long> {
    User findByLogin(String login) throws SQLException;

    List<User> findAllStudent() throws SQLException;

    List<User> findAllTeacher() throws SQLException;
}
