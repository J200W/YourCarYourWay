package com.example.ycyw.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WelcomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to YourCarYourWay!";
    }
}