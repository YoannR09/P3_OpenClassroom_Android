package com.example.mareu.factory;

import com.example.mareu.model.User;
import com.example.mareu.service.UserApiService;

import java.util.List;

public class UserRepository {

    private UserApiService api;

    public UserRepository(UserApiService api) {
        this.api = api;
    }

    public User getUser(int id) {
        return api.getUser(id);
    }

    public List<User> getUsers() {
        return api.getUsers();
    }
}
