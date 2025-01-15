package com.project.ExpenseTracker.controller;

import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> UserRegistration(){
        User user = userService.registerUser();
        return new ResponseEntity<>("Register!!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> UserLogin(){

        return new ResponseEntity<>("Logged In!!", HttpStatus.OK);
    }
}
