package com.example.springbootsmart_Contact_Manager.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FogetController {
    Random random = new Random(1000);

    @GetMapping("/home/forgot")
    public String openEmailForm(Model Model){
        Model.addAttribute("title", "Forget Password");
        return "forgetEmailForm";
    }

    @PostMapping("/home/sendOTP")
    public String sendOtp(@RequestParam("email")String email){
        
        
        int otp = random.nextInt(9999);

        System.out.println(otp);
        System.out.println(email);

        return "verifyOtp";
    }
}
