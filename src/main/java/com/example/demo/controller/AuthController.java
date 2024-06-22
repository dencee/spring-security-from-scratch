package com.example.demo.controller;

import com.example.demo.model.LoginDto;
import com.example.demo.model.LoginResponseDto;
import com.example.demo.security.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Test login with username and password passed through request body
 */
@RestController
public class AuthController {

    private AuthenticationManager authManager;
    private TokenProvider tokenProvider;

    public AuthController(AuthenticationManager authManager, TokenProvider tokenProvider) {
        this.authManager = authManager;
        this.tokenProvider = tokenProvider;
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
         * 3. If auth successful, build jwt & then return it in response
         */
        if(authenticatedUser.isAuthenticated()){

            String jwt = tokenProvider.createToken(authenticatedUser);
            String username = authenticatedUser.getName();

            List<GrantedAuthority> roles = new ArrayList<>(authenticatedUser.getAuthorities());
            String role = roles.size() > 0 ? roles.get(0).getAuthority() : "";

            return new LoginResponseDto(jwt, username, role);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
