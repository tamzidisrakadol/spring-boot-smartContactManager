package com.example.springbootsmart_Contact_Manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SecurityController {

    @GetMapping("/index")
    public String dashboard(){
        return "normal/index";
    }
    
}
