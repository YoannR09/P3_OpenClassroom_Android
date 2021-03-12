package com.example.mareu.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RoomTest {

    Room room = new Room(0,null);

    @Test
    public void setterAndGetterId() {
        room.setId(3);
        int id = room.getId();
        assertEquals(id, 3);
    }

    @Test
    public void setterAndGetterTitle() {
        room.setTitle("title");
        String title = room.getTitle();
        assertEquals(title, "title");
    }
}
