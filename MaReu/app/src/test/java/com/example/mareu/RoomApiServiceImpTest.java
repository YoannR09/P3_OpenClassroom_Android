package com.example.mareu;

import com.example.mareu.model.Room;
import com.example.mareu.service.impl.RoomApiServiceImpl;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoomApiServiceImpTest {

    RoomApiServiceImpl impl = new RoomApiServiceImpl();

    @Test
    public void getRoom() {
        // GIVEN
        Room room;

        // WHEN
        room = impl.getRoom(1);

        // THEN
        assertNotNull(room);
        assertEquals(1, room.getId());
    }

    @Test
    public void getRooms() {
        // GIVEN
        List<Room> vList;

        // WHEN
        vList = impl.getRooms();

        // THEN
        assertNotNull(vList);
        assertEquals(10, vList.size());
    }
}
