package com.example.mareu.service;

import com.example.mareu.model.User;

import java.util.List;

public interface UserApiService {
    User getUser(int id);
    List<User> getUsers();
}
