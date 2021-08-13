package ru.trofimov.timetableviewersystem.service;

import ru.trofimov.timetableviewersystem.model.UserGroup;

import java.sql.SQLException;

public interface UserGroupService {

    UserGroup save(UserGroup entity) throws SQLException;

    UserGroup update(UserGroup entity) throws SQLException;

    void deleteByUserId(Long id) throws SQLException;

    void deleteByGroupId(Long id) throws SQLException;

}
