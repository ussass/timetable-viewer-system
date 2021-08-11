package ru.trofimov.timetableviewersystem.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.trofimov.timetableviewersystem.dao.Entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class User implements Entity<Long> {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Set<Role> roles;
    private Set<SimpleGrantedAuthority> authorities;

    public User(String firstName, String lastName) {
        roles = new HashSet<>();
        authorities = new HashSet<>();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(Long id, String firstName, String lastName, String login, String password, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long value) {
        this.id = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public String getStringRoles() {
        if (roles.size() == 0) return "";
        StringBuilder builder = new StringBuilder();
        roles.forEach(role -> builder.append(role.name()).append(","));
        return builder.toString().substring(0, builder.toString().length() - 1);
    }

    public void setStringRoles(String roles) {
        Arrays.stream(roles.split(","))
                .forEach(s -> {
                    if(s.length() > 2) addRole(Role.valueOf(s));
                });
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        for (Role role: roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.name()));
        }
        return authorities;
    }

    public void setAuthorities(Set<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }

    public boolean isTeacher(){
        return roles.contains(Role.TEACHER);
    }

    public boolean isStudent(){
        return roles.contains(Role.STUDENT);
    }

    public boolean isStuff(){
        return roles.contains(Role.STUFF);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
