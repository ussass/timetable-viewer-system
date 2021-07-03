package ru.trofimov.timetableviewersystem.dao;

import java.sql.SQLException;

public abstract class AbstractDao<T extends Entity<Long>> implements Dao<T, Long> {
    public T save(T entity) throws SQLException {
        return entity.getId() == 0? create(entity) : update(entity);
    }

    abstract public T create(T entity) throws SQLException;

    abstract public T update(T entity) throws SQLException;
}