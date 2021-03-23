package com.example.mareu.service.generator;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class RoomGenerator {

    public static List<Room> ROOMS = Arrays.asList(
            new Room(1,"Room A"),
            new Room(2,"Room B"),
            new Room(3,"Room C"),
            new Room(4,"Room D"),
            new Room(5,"Room E"),
            new Room(6,"Room F"),
            new Room(7,"Room G"),
            new Room(8,"Room H"),
            new Room(9,"Room I"),
            new Room(10,"Room J")
    );

    public static List<Room> generateRooms() {
        return new ArrayList<>(ROOMS);
    }
}
