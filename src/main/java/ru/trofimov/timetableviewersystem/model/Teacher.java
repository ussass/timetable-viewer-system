package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.MyEntity;

public class Teacher extends User implements MyEntity<Long> {

    private long id;
    private Long courseId;
    private String courseName;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNameAndFullName(){
        return courseName + " - " + getFullName();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", " + getFirstName() + " " + getLastName() +
                '}';
    }
}
