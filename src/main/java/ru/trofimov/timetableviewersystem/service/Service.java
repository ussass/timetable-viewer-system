package ru.trofimov.timetableviewersystem.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T, K> {
    T save(T entity) throws SQLException;

    List<T> findAll();

    T findById(K id) throws SQLException;

    T update(T entity) throws SQLException;

    void delete(K id) throws SQLException;
}
