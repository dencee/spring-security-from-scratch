package com.example.demo.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String bearer = "Bearer ";
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith(bearer)){

            String jwt = authHeader.substring(bearer.length());

            /*
             * Of course the jwt would be validated using the
             * jjwt module code and not something hokey like this
             */
            if(jwt.equals("dmc")){
                /*
                 * SUCCESSFUL authentication
                 * Set the user, password, and role
                 * Using this constructor creates an authenticated user by default
                 */
                Set<GrantedAuthority> roles = Set.of(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken authenticatedUser =
                        new UsernamePasswordAuthenticationToken("user", "password", roles);

                /*
                 * Set the security context to successful authentication
                 * Rest of spring security can grab it from the context!
                 */
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                System.out.println("Successful authentication: " + authenticatedUser);
            }
        }

        /*
         * Call the next security filter in the chain
         */
        filterChain.doFilter(request, response);
    }
}
