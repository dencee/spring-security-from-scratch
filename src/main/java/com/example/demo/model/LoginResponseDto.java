package com.example.demo.model;

public class LoginResponseDto {

    private String jwt;
    private String username;
    private String role;

    public LoginResponseDto(String jwt, String username, String role) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
