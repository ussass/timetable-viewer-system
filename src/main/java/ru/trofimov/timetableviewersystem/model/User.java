package ru.trofimov.timetableviewersystem.model;

import java.util.Arrays;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private final List<Role> roles;

    public User(String firstName, String lastName, Role... roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = Arrays.asList(roles);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
