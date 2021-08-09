package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

public class Teacher extends User implements Entity<Long> {

    private long id;

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long value) {
        this.id = value;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", " + getFirstName() + " " + getLastName() +
                '}';
    }
}
