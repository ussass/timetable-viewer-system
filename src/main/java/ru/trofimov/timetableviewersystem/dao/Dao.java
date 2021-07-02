package ru.trofimov.timetableviewersystem.dao;

import java.util.List;

public interface Dao<T, K> {
    T save(T entity);
    List<T> findAll();
    T findById(K id);
    T update(T entity);
    void delete(K id);
}
