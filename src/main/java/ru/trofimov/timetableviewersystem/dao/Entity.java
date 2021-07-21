package ru.trofimov.timetableviewersystem.dao;

public interface Entity<K> {
    K getId();

    void setId(K value);
}
