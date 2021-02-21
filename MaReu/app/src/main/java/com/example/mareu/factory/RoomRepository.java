package com.example.mareu.factory;

import com.example.mareu.model.Room;
import com.example.mareu.service.RoomApiService;

import java.util.List;

public class RoomRepository {

    private RoomApiService api;

    public RoomRepository(RoomApiService api) {
        this.api = api;
    }

    public Room getRoom(int id) {
        return api.getRoom(id);
    }

    public List<Room> getRooms() {
        return api.getRooms();
    }
}
