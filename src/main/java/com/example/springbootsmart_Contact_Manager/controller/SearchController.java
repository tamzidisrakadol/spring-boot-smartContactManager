package com.example.springbootsmart_Contact_Manager.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootsmart_Contact_Manager.dao.ContactRepo;
import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.Contact;
import com.example.springbootsmart_Contact_Manager.modal.User;

@RestController
public class SearchController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepo contactRepo;

    @GetMapping("/home/user/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal) {
        User user = this.userRepository.getUserByEmail(principal.getName());

        List<Contact> contact = this.contactRepo.findByNameContainingAndUser(query, user);
        return ResponseEntity.ok(contact);
    }

}
