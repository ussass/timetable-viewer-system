package ru.trofimov.timetableviewersystem.dao;

public interface MyEntity<K> {
    K getId();

    void setId(K value);
}
