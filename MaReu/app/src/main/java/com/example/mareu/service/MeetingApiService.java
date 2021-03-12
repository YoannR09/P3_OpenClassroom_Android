package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetings();
    List<Meeting> getMeetingsByRoomId(int roomId);
    Meeting getMeeting(int id);
    void deleteMeeting(Meeting meeting);
    void addMeeting(Meeting meeting);
    List<Meeting> filterByHour(int hour);
    List<Meeting> filterByRoom(int roomdId);
    List<Meeting> filterByHourAndRoom(int hour, int roomId);
}
