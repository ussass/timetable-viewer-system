package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

import java.util.Objects;

public class Student extends User implements Entity<Long> {
    private long id;
    private Long groupId;
    private String groupName;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long value) {
        this.id = value;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + id +
                ", groupId=" + groupId +
                ", " + getFirstName() + " " + getLastName() +
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
