package com.epam.faculty.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "faculty_users")
@DynamicInsert
@DynamicUpdate
@NamedQueries({@NamedQuery(name = "facultyUserLogin",
        query = "from FacultyUser f where f.login=:loginParam")})
public class FacultyUser {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", length = 16, nullable = false, updatable = false)
    private UUID userId = UUID.randomUUID();

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "user_blocked", nullable = false)
    private boolean userBlocked;

    @OneToMany(mappedBy = "facultyUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> usersRegistration = new ArrayList<>();

    public FacultyUser() {}

    public FacultyUser(String firstName, String lastName, String login, String password,  String email, String telephone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    public FacultyUser(UUID userId, String firstName, String lastName, String login, String password, String email, String telephone, String role, boolean userBlocked)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.userBlocked = userBlocked;
    }
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isUserBlocked() {
        return userBlocked;
    }

    public void setUserBlocked(boolean userBlocked) {
        this.userBlocked = userBlocked;
    }

    public List<Registration> getRegistrations() {
        return usersRegistration;
    }

    public void setPurchaseList(List<Registration> registrationList) {
        this.usersRegistration = registrationList;
    }

}
