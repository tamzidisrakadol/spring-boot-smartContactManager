package com.example.springbootsmart_Contact_Manager.controller;

import java.util.Random;

import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.Email;
import com.example.springbootsmart_Contact_Manager.modal.User;
import com.example.springbootsmart_Contact_Manager.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgetController {
    Random random = new Random(1000);

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/home/forgot")
    public String openEmailForm(Model Model) {
        Model.addAttribute("title", "Forget Password");
        return "forgetEmailForm";
    }

    @PostMapping("/home/sendOTP")
    public String sendOtp(@RequestParam("email") String email, HttpSession httpSession, Model model) {
        model.addAttribute("title", "OTP");
        int otp = random.nextInt(9999);

        String message = "OTP is " + otp;
        String subject = "mail form Smart Contact Manager";
        Email mail = new Email();
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setMessage(message);

        emailService.sendMail(mail);

        httpSession.setAttribute("myOtp", otp);
        httpSession.setAttribute("email", email);

        return "verifyOtp";
    }


    @PostMapping("/home/verifyOtp")
    public String verifyTheOtp(@RequestParam("otp") int otp, HttpSession httpSession,Model model) {
        model.addAttribute("title","Verify the otp");
        int myOtp = (int) httpSession.getAttribute("myOtp");
        String checkMail = (String) httpSession.getAttribute("email");
        if (myOtp == otp) {
            User getUser = userRepository.getUserByEmail(checkMail);

            if (getUser == null) {
                httpSession.setAttribute("message", "User is not available");
                System.out.println("User is not available");
                return "verifyOtp";
            } else {
                return "changePass";
            }

        } else {
            httpSession.setAttribute("message", "You have the wrong otp");
            System.out.println("You have the wrong otp");
            return "verifyOtp";
        }

    }

    @PostMapping("/home/changeNewPass")
    public String changeNewPassword(@RequestParam("newpassword")String newpassword,HttpSession session){
        String email =(String)session.getAttribute("email");
        User user = userRepository.getUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(newpassword));
        this.userRepository.save(user);
        System.out.println(newpassword +"successfull");
        return "redirect:/home/login";
    }

}
