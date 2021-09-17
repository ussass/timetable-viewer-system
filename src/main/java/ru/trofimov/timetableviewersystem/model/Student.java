package ru.trofimov.timetableviewersystem.model;

public class Student extends User {

    private String groupName;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Student(User user) {

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


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", roles=" + roles +
                ", authorities=" + authorities +
                ", stringRoles='" + stringRoles + '\'' +
                '}';
    }
}
