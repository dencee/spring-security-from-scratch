package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Test endpoints that require JWT authentication
 */
@PreAuthorize("isAuthenticated()")
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "Only authenticated users can see this message!";
    }
}
