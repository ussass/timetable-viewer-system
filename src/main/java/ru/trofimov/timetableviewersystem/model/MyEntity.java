package ru.trofimov.timetableviewersystem.model;

public interface MyEntity<K> {
    K getId();

    void setId(K value);
}
