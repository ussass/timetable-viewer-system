package ru.trofimov.timetableviewersystem.dao;

import java.util.List;

public interface DaoOld<T> {
    void add(T t);
    List<T> findAll();
    T findById(int id);
    void update(T t, int id);
    void delete(int id);
}
