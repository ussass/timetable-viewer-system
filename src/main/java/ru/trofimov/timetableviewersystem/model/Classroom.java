package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

public class Classroom implements Entity<Long> {
    private Long id;
    private int number;

    public Classroom(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
