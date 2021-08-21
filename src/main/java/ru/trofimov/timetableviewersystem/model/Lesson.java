package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

public class Lesson implements Entity<Long> {

    private long id;
    private long courseId;
    private long teacherId;
    private long groupId;
    private long classroomId;
    private long lessonSlotId;
    private int dayOfWeek;

    private String courseName;
    private String groupName;
    private String teacherName;

    public Lesson(long courseId, long teacherId, long groupId, long classroomId, long lessonSlotId, int dayOfWeek) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.classroomId = classroomId;
        this.lessonSlotId = lessonSlotId;
        this.dayOfWeek = dayOfWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public long getLessonSlotId() {
        return lessonSlotId;
    }

    public void setLessonSlotId(int lessonSlotId) {
        this.lessonSlotId = lessonSlotId;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", groupId=" + groupId +
                ", classroomId=" + classroomId +
                ", lessonSlotId=" + lessonSlotId +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
