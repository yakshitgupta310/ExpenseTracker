package com.project.ExpenseTracker.controller;

import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> UserRegistration(@RequestBody User user){
        User existingUser = userService.registerUser(user);
        return new ResponseEntity<>("Registered successfully!!", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> UserLogin(@RequestHeader(HttpHeaders.AUTHORIZATION) String AuthToken){
        User LoggedInUser = userService.loginUser(AuthToken);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return new ResponseEntity<>(authentication.getName(), HttpStatus.OK);}
        return new ResponseEntity<>("Logged In!!", HttpStatus.OK);
    }

    @GetMapping("/user")
    public String getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }
}
