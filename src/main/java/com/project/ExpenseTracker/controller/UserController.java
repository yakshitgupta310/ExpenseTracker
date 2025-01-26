package com.project.ExpenseTracker.controller;

import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.service.UserService;
import com.project.ExpenseTracker.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("/register")
    public ResponseEntity<?> UserRegistration(@RequestBody User user){
        User existingUser = userService.registerUser(user);
        return new ResponseEntity<>("Registered successfully!!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> UserLogin(@RequestBody User user){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if(auth.isAuthenticated()){
            return new ResponseEntity<>(jwtUtils.generateToken(user.getUserName()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Logging in Failed", HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/user")
    public String getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }
}
