package com.morlimoore.piggybank.controllers;

import com.morlimoore.piggybank.DTO.UserDTO;
import com.morlimoore.piggybank.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("userDTO", new UserDTO());
//        return "index";
//    }

    @GetMapping()
    public String index(@RequestParam Optional<String> flag, Model model) {
        if (flag.isPresent()) {
            model.addAttribute("warning", "You need to be logged in with your portal credentials, " +
                        "before you can make any transaction." + '\n' + "Kindly login with them, or register to continue.");
        }

        model.addAttribute("userDTO", new UserDTO());
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("register")
    public String register(@Valid UserDTO userDTO) {
        userService.registerUser(userDTO);
        return "redirect:/";
    }
}
