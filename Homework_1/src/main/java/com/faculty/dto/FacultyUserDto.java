package com.faculty.dto;

/**
 * Created by Aleksandr on 13.11.2020.
 */
public class FacultyUserDto {

    private long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String role;
    private String telephone;
    private boolean userBlocked;

    public FacultyUserDto() {
    }

    public FacultyUserDto(long userId, String firstName, String lastName, String login, String email, String role, String telephone, boolean userBlocked) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.role = role;
        this.telephone = telephone;
        this.userBlocked = userBlocked;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    @Override
    public String toString() {
        return "FacultyUserDto{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userBlocked=" + userBlocked +
                '}';
    }
}
