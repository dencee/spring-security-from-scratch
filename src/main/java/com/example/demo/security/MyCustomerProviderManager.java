package com.example.demo.security;

import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

/*
 * Note: ProviderManager implements AuthenticationManager and a bean of
 *  this type can be used where a AuthenticationManager object is needed,
 *  e.g. in the AuthController
 */
@Component
public class MyCustomerProviderManager extends ProviderManager {

    private DaoAuthenticationProvider daoAuthenticationProvider;

    public MyCustomerProviderManager(DaoAuthenticationProvider provider) {
        super(provider);
    }
}
