package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.MyEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group implements MyEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private long id;

    @Column(name = "group_name")
    private String groupName;

    @Transient
    private final List<Student> students;

    public Group() {
        students = new ArrayList<>();
    }

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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }
}
