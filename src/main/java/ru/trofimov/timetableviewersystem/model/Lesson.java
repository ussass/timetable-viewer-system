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

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
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

    public int getDate() {
        return dayOfWeek;
    }

    public void setDate(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
