package com.example.springbootsmart_Contact_Manager.controller;

import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.User;
import com.example.springbootsmart_Contact_Manager.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

   @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Home-Smart Contact Manager");
        return "home";
    }


    @GetMapping("/home/signUp")
    public String signUp(Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute("user",new User());
        return "signUp";
    }


    @GetMapping("/home/about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "about";
    }

    @GetMapping("/home/login")
    public String login(Model model) {
        model.addAttribute("title", "Log In");
        return "logIn";
    }

    @PostMapping("/do_register")
    public String process(@Valid @ModelAttribute("user")User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "signUp";
        }

        user.setRole("ROLE_USER");
        user.setEnabled(true);
        user.setImgUrl("default.png");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        System.out.println(result);
        return "about";
    }


}
