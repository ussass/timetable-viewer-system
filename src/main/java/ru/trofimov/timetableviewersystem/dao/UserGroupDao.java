package ru.trofimov.timetableviewersystem.dao;

import ru.trofimov.timetableviewersystem.model.UserGroup;

import java.sql.SQLException;

public interface UserGroupDao {

    UserGroup save(UserGroup entity) throws SQLException;

    UserGroup update(UserGroup entity) throws SQLException;

    void deleteByUserId(Long id) throws SQLException;

    void deleteByGroupId(Long id) throws SQLException;
}
