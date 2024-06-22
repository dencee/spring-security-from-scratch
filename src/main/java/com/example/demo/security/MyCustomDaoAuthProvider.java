package com.example.demo.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCustomDaoAuthProvider extends DaoAuthenticationProvider {

    private PasswordEncoder encoder;
    private UserDetailsService userDetailsService;

    public MyCustomDaoAuthProvider(PasswordEncoder encoder, UserDetailsService userDetailsService) {
        this.setPasswordEncoder(encoder);
        this.setUserDetailsService(userDetailsService);
    }
}
