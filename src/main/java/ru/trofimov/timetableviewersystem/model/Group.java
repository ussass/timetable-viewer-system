package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

import java.util.ArrayList;
import java.util.List;

public class Group implements Entity<Long> {
    private long id;
    private String groupName;
    private final List<Student> students;

    public Group(String groupName) {
        this.groupName = groupName;
        students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long groupId) {
        this.id = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
