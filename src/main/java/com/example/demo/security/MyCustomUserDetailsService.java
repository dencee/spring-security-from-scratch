package com.example.demo.security;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    public MyCustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         * Get user data from database and return a spring user
         * with username, password, and roles
         */
        User user = this.userDao.getUserByUsername(username);
        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(), roles);
    }

}
