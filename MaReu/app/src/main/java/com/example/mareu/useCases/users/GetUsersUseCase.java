package com.example.mareu.useCases.users;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;

import java.util.List;

public class GetUsersUseCase {

    public List<User> call() {
        return Repositories.getUserRepository().getUsers();
    }
}
