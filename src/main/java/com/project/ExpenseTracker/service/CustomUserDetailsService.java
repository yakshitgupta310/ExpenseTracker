package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.CustomUserDetails;
import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByuserName(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User is not registered! Please register.");
        }
        return new CustomUserDetails(user.get());
    }
}
