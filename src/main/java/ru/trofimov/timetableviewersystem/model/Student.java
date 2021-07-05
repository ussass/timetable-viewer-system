package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

import java.util.Objects;

public class Student extends User implements Entity<Long> {
    private long id;
    private int groupId;

    public Student(String firstName, String lastName, Role... roles) {
        super(firstName, lastName, roles);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long value) {
        this.id = value;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + id +
                ", groupId=" + groupId +
                ", " + getFirstName() + " "  + getLastName() +
                '}';
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
