package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

public class Course implements Entity<Long> {
    private Long courseId;
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public Long getId() {
        return courseId;
    }

    @Override
    public void setId(Long value) {
        this.courseId = value;
    }
}
