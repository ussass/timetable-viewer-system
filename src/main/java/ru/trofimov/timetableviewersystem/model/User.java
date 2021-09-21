package ru.trofimov.timetableviewersystem.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.trofimov.timetableviewersystem.dao.MyEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements MyEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    protected Long id;

    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    protected String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be null")
    protected String lastName;

    @Size(min = 4)
    protected String login;

    @Size(min = 8)
    protected String password;

    @Column(name = "course_id")
    protected Long courseId;

    @Column(name = "group_id")
    protected Long groupId;

    @Transient
    protected Set<Role> roles;

    @Transient
    protected Set<SimpleGrantedAuthority> authorities;

    @Column(name = "roles")
    protected String stringRoles;

    public User() {
        roles = new HashSet<>();
        authorities = new HashSet<>();
    }

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

    public Long getCourseId() {
        return courseId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getStringRoles() {
        return stringRoles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public String setRolesToStringRoles() {
        if (roles.size() == 0) {
            stringRoles = "";
        } else {
            StringBuilder builder = new StringBuilder();
            roles.forEach(role -> builder.append(role.name()).append(","));
            stringRoles = builder.toString().substring(0, builder.toString().length() - 1);
        }
        return stringRoles;
    }

    public void addRolesFromString(String roles) {
        Arrays.stream(roles.split(","))
                .forEach(s -> {
                    if (s.length() > 2) addRole(Role.valueOf(s));
                });
    }

    public void addRolesFromString() {
        Arrays.stream(stringRoles.split(","))
                .forEach(s -> {
                    if (s.length() > 2) addRole(Role.valueOf(s));
                });
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public void setStringRoles(String stringRoles) {
        this.stringRoles = stringRoles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }
        return authorities;
    }

    public void setAuthorities(Set<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isTeacher() {
        return roles.contains(Role.TEACHER);
    }

    public boolean isStudent() {
        return roles.contains(Role.STUDENT);
    }

    public boolean isStuff() {
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
                ", courseId='" + courseId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", roles=" + roles +
                ", stringRoles=" + stringRoles +
                '}';
    }
}
