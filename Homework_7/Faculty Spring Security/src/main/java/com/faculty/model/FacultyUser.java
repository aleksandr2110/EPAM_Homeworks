package com.faculty.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class FacultyUser implements Serializable {

    private long userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String telephone;
    private String role;
    private boolean userBlocked;

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

    public FacultyUser(long userId, String firstName, String lastName, String login, String password, String email, String telephone, String role, boolean userBlocked)
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

//    public boolean getUserBlocked()
//    {
//        return userBlocked;
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacultyUser that = (FacultyUser) o;

        if (userId != that.userId) return false;
        if (userBlocked != that.userBlocked) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!login.equals(that.login)) return false;
        if (!password.equals(that.password)) return false;
        if (!email.equals(that.email)) return false;
        if (!role.equals(that.role)) return false;
        return telephone.equals(that.telephone);
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + telephone.hashCode();
        result = 31 * result + (userBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FacultyUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", role='" + role + '\'' +
                ", userBlocked=" + userBlocked +
                '}';
    }
}
