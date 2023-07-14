package com.example.springbootsmart_Contact_Manager.controller;

import com.example.springbootsmart_Contact_Manager.modal.User;
import com.example.springbootsmart_Contact_Manager.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("title","Home-Smart Contact Manager");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About");
        return "about";
    }

    @GetMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("title", "Sign Up");
        return "signUp";
    }

    
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("title", "Log In");
        return "logIn";
    }

       @PostMapping("/do_register")
    public String process(@Valid @ModelAttribute("user") User user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            
            System.out.println(bindingResult);
            return "signUp";
        }
        System.out.println(user);
        return "sucess";
    }


}
