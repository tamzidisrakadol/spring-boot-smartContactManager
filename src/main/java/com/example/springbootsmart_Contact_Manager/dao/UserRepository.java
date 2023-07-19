package com.example.springbootsmart_Contact_Manager.dao;

import com.example.springbootsmart_Contact_Manager.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

   @Query("select u from User u where u.email = :email")
   public User getUserByEmail(@Param("email") String email);


}
