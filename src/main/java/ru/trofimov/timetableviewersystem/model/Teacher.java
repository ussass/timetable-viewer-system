package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

public class Teacher extends User implements Entity<Long> {

    private long id;

    public Teacher(String firstName, String lastName, Role... roles) {
        super(firstName, lastName, roles);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long value) {
        this.id = value;
    }
}
