package com.example.neksus.models;

import java.util.Date;

public class User {
    private String username;
    private String email;
    private String password;
    private char isBanned;
    private char isAdmin;
    private Date registerDate;
    private String employeeId;

    // Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public char getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(char isBanned) {
        this.isBanned = isBanned;
    }

    public char getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
