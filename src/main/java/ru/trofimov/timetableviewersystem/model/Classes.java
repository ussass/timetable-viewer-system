package ru.trofimov.timetableviewersystem.model;

import ru.trofimov.timetableviewersystem.dao.Entity;

import java.util.Date;

public class Classes implements Entity<Long> {

    private long id;
    private long courseId;
    private long teacherId;
    private long groupId;
    private long classroomId;
    private long lessonSlotId;
    private Date date;

    public Classes(long courseId, long teacherId, long groupId, long classroomId, long lessonSlotId, Date date) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.groupId = groupId;
        this.classroomId = classroomId;
        this.lessonSlotId = lessonSlotId;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateAndTime() {
        return date.getDate() + "." + (date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1) + "."
                + (date.getYear() + 1900) + " " + date.getHours() + ":" + date.getMinutes();
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", teacherId=" + teacherId +
                ", groupId=" + groupId +
                ", classroomId=" + classroomId +
                ", lessonSlotId=" + lessonSlotId +
                ", date=" + date +
                '}';
    }
}
