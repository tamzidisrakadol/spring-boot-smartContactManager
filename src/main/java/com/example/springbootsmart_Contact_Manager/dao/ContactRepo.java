package com.example.springbootsmart_Contact_Manager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springbootsmart_Contact_Manager.modal.Contact;

public interface ContactRepo extends JpaRepository<Contact,Integer>{

    @Query("from Contact as c where c.user.id =:userID")
    public List<Contact> findContactByUser(@Param("userID")int userID);
    
    
}
