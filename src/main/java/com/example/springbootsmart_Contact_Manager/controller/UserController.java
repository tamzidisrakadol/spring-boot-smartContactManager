package com.example.springbootsmart_Contact_Manager.controller;

import com.example.springbootsmart_Contact_Manager.modal.User;
import com.example.springbootsmart_Contact_Manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        User user = new User();
        user.setUserName("israk");
        user.setEmail("israk@gmail.com");
        userService.saveUser(user);
        return "Success to save";
    }
}
