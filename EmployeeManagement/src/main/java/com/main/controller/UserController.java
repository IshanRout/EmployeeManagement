package com.main.controller;

import com.main.dto.UserDTO;
import com.main.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//controller class for adding the user
@Controller
@RequestMapping("/addUser")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDTO userRegistrationDto() {
        return new UserDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDTO registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }

}
