package com.example.mareu.model;

import java.util.List;

public class Meeting {

    private int id;
    private int start;
    private String sujet;
    private int roomId;
    private Room room;
    private int hour;
    private int min;
    private List<User> users;
    private List<Participant> participants;

    public Meeting(int id, String sujet, int roomId,
                   int hour, int min) {
        this.id = id;
        this.sujet = sujet;
        this.roomId = roomId;
        this.hour = hour;
        this.min = min;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
