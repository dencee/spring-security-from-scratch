package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/*
 * Test login with username and password passed through request body
 */
@RestController
public class AuthController {

    private AuthenticationManager authManager;

    public AuthController(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginBody){

        /*
         * 1. Get an AuthenticationManager to validate the username & password
         */
        UsernamePasswordAuthenticationToken unauthenticatedUser =
                new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());

        /*
         * 2. Check Username & password using AuthenticationManager
         *  This will call DaoAuthenticationProvider -> UserDetailsService (get user from db)
         *  then validate the request password with the password in the db
         */
        Authentication authenticatedUser = this.authManager.authenticate(unauthenticatedUser);

        /*
         * 3. If auth successful, build jwt & then return jwt
         */
        if(authenticatedUser.isAuthenticated()){
            /*
             * TODO: Send JWT token
             */
            System.out.println("authenticated");
            return new LoginResponseDto("JWT sent!!!");
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}

class LoginResponseDto {
    private String jwt;

    public LoginResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}

class LoginDto {
    private String username;
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}