package com.example.demo2.service;

import com.example.demo2.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService 
{
    UserDetails loadUserByUsername(String username);
    User saveUser(User user);
}