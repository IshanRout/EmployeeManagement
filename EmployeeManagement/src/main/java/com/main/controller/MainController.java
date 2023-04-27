package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//controller class for the user login

@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
