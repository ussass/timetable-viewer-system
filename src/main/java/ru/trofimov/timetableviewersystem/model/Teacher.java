package ru.trofimov.timetableviewersystem.model;

public class Teacher extends User{

    public Teacher(String firstName, String lastName, Role... roles) {
        super(firstName, lastName, roles);
    }
}
