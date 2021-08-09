package ru.trofimov.timetableviewersystem.service;


import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;

public interface UserService extends Service<User, Long> {
    User findByLogin(String login) throws SQLException;
}
