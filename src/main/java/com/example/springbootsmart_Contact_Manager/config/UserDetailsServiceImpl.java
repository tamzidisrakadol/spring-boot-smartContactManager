package com.example.springbootsmart_Contact_Manager.config;

import com.example.springbootsmart_Contact_Manager.dao.UserRepository;
import com.example.springbootsmart_Contact_Manager.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user==null){
            throw new UsernameNotFoundException("Could not found user");
        }
        return new CustomerUserDetails(user);
    }
}
