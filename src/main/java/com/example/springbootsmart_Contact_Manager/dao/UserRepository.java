package com.example.springbootsmart_Contact_Manager.dao;

import com.example.springbootsmart_Contact_Manager.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
