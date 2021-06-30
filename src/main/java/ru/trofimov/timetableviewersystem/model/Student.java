package ru.trofimov.timetableviewersystem.model;

import java.util.Objects;

public class Student extends User{
    private int studentId;
    private Group group;

    public Student(String firstName, String lastName, Role... roles) {
        super(firstName, lastName, roles);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getFirstName(), student.getFirstName())
                && Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}
