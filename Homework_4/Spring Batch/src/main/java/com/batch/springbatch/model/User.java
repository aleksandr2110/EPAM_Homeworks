package com.batch.springbatch.model;

public class User {

    private String firstName;
    private String lastName;
    private Double balance;
    private String email;

    public User(String firstName, String lastName, Double balance, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.email = email;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
