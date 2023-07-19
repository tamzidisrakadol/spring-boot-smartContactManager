package com.example.springbootsmart_Contact_Manager.controller;

import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.Contact;
import com.example.springbootsmart_Contact_Manager.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/home")
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    @ModelAttribute
    public void addCommondata(Model model,Principal principal){
        String email = principal.getName();
        User user = userRepository.getUserByEmail(email);
        model.addAttribute("user", user);
    }

    @GetMapping("/user/index")
    public String dashboard(Model model,Principal principal){
        String email = principal.getName();
        User user = userRepository.getUserByEmail(email);
        model.addAttribute("user", user);
        return "normal/index";
    }

    @GetMapping("/admin/index")
    public String adminDashboard(){
        return "admin/dashboard";
    }

    @GetMapping("/user/add-contact")
    public String userAddContactForm(Model model){
        model.addAttribute("title", "Add Contact");
        model.addAttribute("contact", new Contact());
        return "normal/addcontact";
    }


    @PostMapping("/user/saveContact")
    public String saveToDB(@ModelAttribute("contact")Contact contact, Principal principal){
        String email = principal.getName();
        User emailUserByName=userRepository.getUserByEmail(email);
        contact.setUser(emailUserByName);
        emailUserByName.getContactList().add(contact);
        this.userRepository.save(emailUserByName);
        return "normal/addcontact";
    }


}
