package com.example.mareu.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeetingTest {

    private Meeting meeting = new Meeting(0,null,0,0,0);

    private int id;
    private int start;
    private String sujet;
    private int roomId;
    private Room room;
    private int hour;
    private int min;
    private List<User> users;
    private List<Participant> participants;

    @Test
    public void getterAndSetterId() {
        meeting.setId(3);
        int id = meeting.getId();
        assertEquals(id, 3);
    }

    @Test
    public void getterAndSetterStart() {
        meeting.setStart(32);
        int start = meeting.getStart();
        assertEquals(start, 32);
    }

    @Test
    public void getterAndSetterSujet() {
        meeting.setSujet("sujet");
        String sujet = meeting.getSujet();
        assertEquals(sujet, "sujet");
    }

    @Test
    public void getterAndSetterRoomId() {
        meeting.setRoomId(1);
        int roomId = meeting.getRoomId();
        assertEquals(roomId, 1);
    }

    @Test
    public void getterAndSetterRoom() {
        meeting.setRoom(new Room(3,"Title"));
        Room room = meeting.getRoom();
        assertEquals(room.getId(), 3);
    }

    @Test
    public void getterAndSetterHour() {
        meeting.setHour(22);
        int hour = meeting.getHour();
        assertEquals(hour, 22);
    }

    @Test
    public void getterAndSetterMin() {
        meeting.setMin(33);
        int min = meeting.getMin();
        assertEquals(min, 33);
    }
}
