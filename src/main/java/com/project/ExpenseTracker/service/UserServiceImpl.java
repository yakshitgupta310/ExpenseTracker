package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser() {
        return null;
    }
}
