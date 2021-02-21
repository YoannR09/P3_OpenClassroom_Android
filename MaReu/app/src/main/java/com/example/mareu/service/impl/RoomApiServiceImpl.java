package com.example.mareu.service.impl;

import com.example.mareu.model.Room;
import com.example.mareu.service.RoomApiService;
import com.example.mareu.service.generator.RoomGenerator;

import java.util.List;

public class RoomApiServiceImpl implements RoomApiService {

    private List<Room> rooms = RoomGenerator.generateRooms();

    @Override
    public Room getRoom(int id) {
        Room roomFind = null;
        for(Room r: rooms) {
            if (r.getId() == id) {
                roomFind = r;
            }
        }
        return roomFind;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }
}
