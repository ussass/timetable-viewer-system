package ru.trofimov.timetableviewersystem.model;

public class Teacher extends User {

    private String courseName;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Teacher(User user) {

        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.courseId = user.getCourseId();
        this.groupId = user.getGroupId();
        this.roles = user.getRoles();
        this.stringRoles = user.getStringRoles();
    }


    public String getCourseName() {
        return courseName;
    }

    public String getCourseNameAndFullName(){
        return courseName + " - " + getFullName();
    }

    public void setCourseName(String groupName) {
        this.courseName = groupName;
    }

    @Override
    public String toString() {
        return "NewStudent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", groupId=" + groupId +
                ", roles=" + roles +
                ", authorities=" + authorities +
                ", stringRoles='" + stringRoles + '\'' +
                '}';
    }
}
