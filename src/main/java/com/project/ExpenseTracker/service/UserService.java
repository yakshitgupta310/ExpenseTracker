package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.User;

public interface UserService {

    public User registerUser(User user);

    public User loginUser(String AuthToken);
}
