package ru.trofimov.timetableviewersystem.service;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<T, K> {
    T save(T entity) throws SQLException;

    List<T> findAll() throws SQLException;

    T findById(K id) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(K id) throws SQLException;
}
