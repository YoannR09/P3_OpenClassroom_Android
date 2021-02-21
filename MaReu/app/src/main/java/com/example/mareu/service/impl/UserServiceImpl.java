package com.example.mareu.service.impl;

import com.example.mareu.model.User;
import com.example.mareu.service.UserApiService;
import com.example.mareu.service.generator.UserGenerator;

import java.util.List;

public class UserServiceImpl implements UserApiService {

    private List<User> users = UserGenerator.generateUsers();

    @Override
    public User getUser(int id) {
        User userToReturn = null;
        for(User user: users) {
            if (user.getId() == id){
                userToReturn = user;
            }
        }
        return userToReturn;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
