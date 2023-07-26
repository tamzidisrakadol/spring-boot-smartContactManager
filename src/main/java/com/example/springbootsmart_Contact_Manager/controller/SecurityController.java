package com.example.springbootsmart_Contact_Manager.controller;

import com.example.springbootsmart_Contact_Manager.dao.ContactRepo;
import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.Contact;
import com.example.springbootsmart_Contact_Manager.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home")
public class SecurityController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepo contactRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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


    @GetMapping("/user/showContact/{page}")
    public String showContact(@PathVariable("page")Integer page, Model model,Principal principal){
        model.addAttribute("title","Show Contact");
        String email = principal.getName();
        User emailUser =this.userRepository.getUserByEmail(email);
        Pageable pageable = PageRequest.of(page, 5);

        Page<Contact> contacts = this.contactRepo.findContactByUser(emailUser.getId(),pageable);
        model.addAttribute("contacts", contacts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", contacts.getTotalPages());

        return "normal/showContact";
    }

    @RequestMapping("/user/{cId}/showContact")
    public String contactDetail(@PathVariable("cId")Integer cId,Model model,Principal principal){
        Optional<Contact> cont = this.contactRepo.findById(cId);
        Contact contact = cont.get();
        String email = principal.getName();
        User findByEmail =this.userRepository.getUserByEmail(email);

        //checking the right logged-in user
        if(findByEmail.getId()==contact.getUser().getId()){
            model.addAttribute("contact", contact);
        }
        
        return "normal/contactDetail";
    }


    @GetMapping("/user/{cId}/deleteContact")
    public String deleteContact(@PathVariable("cId")Integer cId, Model model,Principal principal){
        Contact contact = this.contactRepo.findById(cId).get();
        
        String email = principal.getName();
        User findByEmail =this.userRepository.getUserByEmail(email);
        if(findByEmail.getId()==contact.getUser().getId()){
            findByEmail.getContactList().remove(contact);
            this.userRepository.save(findByEmail);
        }
        return "redirect:/home/user/showContact/0";
    }



    @RequestMapping("/user/{cId}/updateContact")
    public String updateContact(@PathVariable("cId")Integer cId, Model model){
        model.addAttribute("title", "Update Contact");
        Contact contact = this.contactRepo.findById(cId).get();
        model.addAttribute("contact", contact);
        return "normal/updateContact";
    }


    @PostMapping("/user/updating")
    public String updateContactDetails(@ModelAttribute Contact contact, Principal principal){
        User user = this.userRepository.getUserByEmail(principal.getName());
        contact.setUser(user);
        this.contactRepo.save(contact);
        return "normal/updateContact";
    }



    @GetMapping("/user/profile")
    public String profile(Model model){
        model.addAttribute("title", "Profile");
        return "normal/profile";
    }


    @GetMapping("/user/setting")
    public String setting(Model model){
        model.addAttribute("title", "setting");
        return "normal/setting";
    }


    @PostMapping("/user/changePassword")
    public String changePassword(@RequestParam("oldPassword")String oldPassword,@RequestParam("newPassword")String newPassword,Principal principal){
        String userName = principal.getName();
        User currentUser=this.userRepository.getUserByEmail(userName);

        if(this.bCryptPasswordEncoder.matches(oldPassword,currentUser.getPassword())){
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.save(currentUser);
            System.out.println("Success");
             return "redirect:/home/user/index";
        }else{
            System.out.println("There is something error");
            return "normal/setting";
        }
    }

}
