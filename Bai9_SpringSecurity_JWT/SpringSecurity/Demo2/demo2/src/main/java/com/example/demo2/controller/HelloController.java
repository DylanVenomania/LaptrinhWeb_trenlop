package com.example.demo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController 
{

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World! (Public access)";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin Page (ROLE_ADMIN only)";
    }

    @GetMapping("/user")
    public String user() {
        return "User Page (ROLE_USER or ADMIN)";
    }
}