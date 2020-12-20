package com.epam.faculty.dto;

import java.util.UUID;


public class FacultyUserDto {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String telephone;
    private String role;
    private boolean userBlocked;

    public FacultyUserDto() {}

    public FacultyUserDto(String firstName, String lastName, String login, String password,  String email, String telephone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    public FacultyUserDto(UUID userId, String firstName, String lastName, String login, String password, String email, String telephone, String role, boolean userBlocked)
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
}
