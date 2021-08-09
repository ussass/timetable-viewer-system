package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.User;

import java.sql.SQLException;

public interface UserDao extends Dao<User, Long> {
    User findByLogin(String login) throws SQLException;
}
