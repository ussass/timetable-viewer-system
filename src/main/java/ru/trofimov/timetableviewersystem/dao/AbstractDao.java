package ru.trofimov.timetableviewersystem.dao;

public abstract class AbstractDao<T extends Entity<Long>> implements Dao<T, Long> {
    public T save(T entity){
        return entity.getId() == 0? create(entity) : update(entity);
    }

    abstract public T create(T entity);

    abstract public T update(T entity);
}
