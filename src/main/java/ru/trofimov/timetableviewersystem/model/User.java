package ru.trofimov.timetableviewersystem.model;

import java.util.Arrays;
import java.util.List;

public class User {
    protected String firstName;
    protected String lastName;
    protected List<Role> roles;

    public User(String firstName, String lastName, Role... roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = Arrays.asList(roles);
    }
}
