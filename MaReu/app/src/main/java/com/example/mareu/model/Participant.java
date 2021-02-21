package com.example.mareu.model;

public class Participant {

    private int userId;
    private int meetingId;

    public Participant(int userId, int meetingId) {
        this.userId = userId;
        this.meetingId = meetingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }
}
