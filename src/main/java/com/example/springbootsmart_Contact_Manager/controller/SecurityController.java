package com.example.springbootsmart_Contact_Manager.controller;

import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/home")
public class SecurityController {

    @GetMapping("/user/index")
    public String dashboard(){
        return "normal/index";
    }

    @GetMapping("/admin/index")
    public String adminDashboard(){
        return "admin/dashboard";
    }


}
