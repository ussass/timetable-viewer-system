package ru.trofimov.timetableviewersystem.model;

public class UserCourse {

    private Long userId;

    private Long courseId;

    public UserCourse(Long userId, Long courseId) {
        this.userId = userId;
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "userId=" + userId +
                ", courseId=" + courseId +
                '}';
    }
}
