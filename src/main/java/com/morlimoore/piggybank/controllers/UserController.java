package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String root(Model model) {
        System.out.println("Application root hit");
        model.addAttribute(new UserDTO());
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        System.out.println("In dashboard: " + session.getAttribute("user"));
        return "dashboard";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserDTO userDTO) {
        userService.registerUser(userDTO);
        System.out.println("Registration successful");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserDTO userDTO, HttpSession session) {
        String response = "";
        UserDTO tempUserDTO = userService.loginUser(userDTO);
        if (tempUserDTO.getEmail().isBlank()) {
            response = "redirect:/";
            System.out.println("Error logging in");
        } else {
            response = "redirect:/dashboard";
            session.setAttribute("user", tempUserDTO);
            System.out.println("Log in success");
        }
        return response;
    }

}
