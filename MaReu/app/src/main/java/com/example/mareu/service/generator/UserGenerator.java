package com.example.mareu.service.generator;

import com.example.mareu.model.Room;
import com.example.mareu.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class UserGenerator {

    public static List<User> USERS = Arrays.asList(
            new User(1, "val12@gmail.com"),
            new User(2,"patrick78@hotmail.fr"),
            new User(3, "yoannroche@outlook.fr"),
            new User(4, "amandine@gmail.com"),
            new User(5, "maxime2@aol.com"),
            new User(6,"michel@gmail.com"),
            new User(7, "steveio@outlook.fr"),
            new User(8, "frabricemail@aol.com"),
            new User(9,"etiennedev@gmail.com"),
            new User(10, "alicemymail@hotmail.fr")
    );

    public static List<User> generateUsers() {
        return new ArrayList<>(USERS);
    }
}
