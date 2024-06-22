package com.example.demo.model;

public class User {

    private int userId;
    private String username;
    private String hashedPassword;
    private String role;

    public User(int userId, String username, String hashedPassword, String role) {
        this.userId = userId;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getRole() {
        return role;
    }
}
