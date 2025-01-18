package com.project.ExpenseTracker.controller;

import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> UserRegistration(@RequestBody User user){
        User existingUser = userService.registerUser(user);
        return new ResponseEntity<>("Registered successfully!!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> UserLogin(@RequestHeader(HttpHeaders.AUTHORIZATION) String AuthToken){
        User LoggedInUser = userService.loginUser(AuthToken);
        return new ResponseEntity<>("Logged In!!", HttpStatus.OK);
    }
}
