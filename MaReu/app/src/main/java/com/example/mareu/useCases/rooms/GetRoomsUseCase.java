package com.example.mareu.useCases.rooms;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Room;

import java.util.List;

public class GetRoomsUseCase {

    public List<Room> call() {
        return Repositories.getRoomRepository().getRooms();
    }
}
