package com.health.management.config;

import com.health.management.entity.User;
import com.health.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //fetching user from database

        User user = userRepository.findByEmail(email);
        System.out.println(user);
        if(user == null) {
            throw new UsernameNotFoundException("Could not found user !!");
        }

        return new CustomsUserDetails(user);
    }
}
