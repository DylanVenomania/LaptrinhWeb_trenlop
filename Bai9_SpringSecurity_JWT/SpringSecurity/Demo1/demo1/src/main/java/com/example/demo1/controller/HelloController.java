package com.example.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController 
{

    @GetMapping("/hello")
    public String hello() 
    {
        return "Hello, World! (Public access)";
    }

    @GetMapping("/admin")
    public String admin() 
    {
        return "Admin Page (ROLE_ADMIN only)";
    }

    @GetMapping("/user")
    public String user() 
    {
        return "User Page (ROLE_USER or ADMIN)";
    }
    
    
    @PostMapping("/greet")
    public String greet(@RequestParam @NotBlank(message = "Name cannot be blank") String name) 
    {
        return "Hello, " + name + "!";
    }
}