package com.libis.model;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
    }

    public User(int memberId, String firstName, String lastName, String email, String password) {
        this.userId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getMemberId() {
        return userId;
    }

    public void setMemberId(int memberId) {
        this.userId = memberId;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
