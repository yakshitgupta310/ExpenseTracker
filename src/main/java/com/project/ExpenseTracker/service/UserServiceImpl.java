package com.project.ExpenseTracker.service;

import com.project.ExpenseTracker.entity.User;
import com.project.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) throws RuntimeException{
        Optional<User> existingUser = userRepository.findByuserName(user.getUserName());
                //userRepository.FindByusername(user.getUserName());
        if(existingUser.isPresent()){
            throw new RuntimeException("User already registered!!");
        }
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        newUser.setRole("USER");
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User loginUser(String AuthToken) {
        //String UserName =

        return null;
    }
}
