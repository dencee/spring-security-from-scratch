package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO Prep:
 *  Remove CRSF and disable sessions
 *  Create security and controller packages
 *
 * TODO 1: Setup login flow
 *  Create WebSecurityConfig class
 * 			Add configure() method
 * 	Create AuthController with POST /login endpoint
 * 	Create AuthenticationManager Bean
 * 			Create UserDetailsService object
 * 	Test endpoint
 *
 * TODO 2: Setup JWT authentication flow
 *  Create JwtFilter
 *  	    Get JWT from request header
 *  	    Validate JWT
 *  	    Set security context with UsernamePasswordAuthenticationToken
 *  Add Jwt Filter to security filter chain
 *  Create TestController
 *  		Create handler method requiring authentication
 *  Test endpoint
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
