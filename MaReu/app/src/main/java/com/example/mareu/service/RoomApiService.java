package com.example.mareu.service;

import com.example.mareu.model.Room;

import java.util.List;

public interface RoomApiService {

    Room getRoom(int id);
    List<Room> getRooms();
}
