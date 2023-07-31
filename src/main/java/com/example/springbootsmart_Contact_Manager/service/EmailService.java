package com.example.springbootsmart_Contact_Manager.service;

import com.example.springbootsmart_Contact_Manager.modal.Email;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(Email email){

        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("israkadol12@gmail.com");
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getMessage());
            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
