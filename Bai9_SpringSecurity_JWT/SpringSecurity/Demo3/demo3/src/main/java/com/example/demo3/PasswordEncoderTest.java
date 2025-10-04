package com.example.demo3;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest 
{
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123"; // Thay bằng password bạn muốn mã hóa
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded password for " + rawPassword + ": " + encodedPassword);
    }
}