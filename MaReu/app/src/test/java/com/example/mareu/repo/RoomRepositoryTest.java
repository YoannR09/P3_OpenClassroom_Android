package com.example.mareu.repo;

import com.example.mareu.factory.RoomRepository;
import com.example.mareu.model.Room;
import com.example.mareu.service.RoomApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomRepositoryTest {

    private RoomApiService api;
    private RoomRepository repository;

    @Test
    public void getRoom() {
        // GIVEN
        Room room = new Room(3,"Title");
        when(api.getRoom(anyInt())).thenReturn(room);
        Room roomResult;

        // WHEN
        roomResult = repository.getRoom(3);

        // THEN
        assertEquals(3,roomResult.getId());
        assertEquals("Title",roomResult.getTitle());
    }

    @Test
    public void getRooms() {
        // GIVEN
        List<Room> rooms = new ArrayList<>();
        Room room1 = new Room(3,"Title1");
        Room room2 = new Room(5,"Title2");
        rooms.add(room1);
        rooms.add(room2);
        when(api.getRooms()).thenReturn(rooms);

        // WHEN
        List<Room> vList = repository.getRooms();

        // THEN
        assertEquals(2, vList.size());
        assertEquals(vList.get(0).getId(),3);
        assertEquals(vList.get(1).getId(),5);
    }

    @Before
    public void init(){
        api = mock(RoomApiService.class);
        repository = new RoomRepositoryFake(api);
    }

    public class RoomRepositoryFake extends RoomRepository {
        public RoomRepositoryFake(RoomApiService api) {
            super(api);
        }
    }
}
