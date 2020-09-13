package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public void rootService() {
        System.out.println("Application root hit");
    }

    @PostMapping("/register")
    public void registerUser(@Valid UserDTO userDTO) {
        System.out.println("Register route hit");
        System.out.println(userDTO);
    }
}
