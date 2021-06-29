package ru.trofimov.timetableviewersystem.model;

public class Student extends User{
    private int studentId;
    private Group group;

    public Student(String firstName, String lastName, Role... roles) {
        super(firstName, lastName, roles);
    }
}
