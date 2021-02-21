package com.example.mareu.factory;

import com.example.mareu.di.Repositories;
import com.example.mareu.di.UseCases;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Participant;
import com.example.mareu.model.User;
import com.example.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    MeetingApiService api;

    public MeetingRepository(MeetingApiService api) {
        this.api = api;
    }

    /**
     * Get list of meetings
     * define room for every meetings
     * define participants then users for every meetings
     * @return
     */
    public List<Meeting> getMeetings() {
        List<Meeting> vList = api.getMeetings();
        initList(vList);
        return vList;
    }

    public List<Meeting> getMeetingsByRoomId(int roomId) {
        List<Meeting> vList = api.getMeetingsByRoomId(roomId);
        initList(vList);
        return vList;
    }

    public Meeting getMeeting(int id) {
        return api.getMeeting(id);
    }

    public void deleteMeeting(Meeting meeting) {
        api.deleteMeeting(meeting);
    }

    public void addMeeting(Meeting meeting) {
        api.addMeeting(meeting);
    }

    public void initList(List<Meeting> list) {
        for(Meeting meeting: list) {
            meeting.setRoom(Repositories.getRoomRepository().getRoom(meeting.getRoomId()));
            meeting.setParticipants(Repositories.getParticipantRepository()
                    .getParticipantByMeetingId(meeting.getId()));
            List<User> users = new ArrayList<>();
            for(Participant p: meeting.getParticipants()){
                users.add(Repositories.getUserRepository().getUser(p.getUserId()));
            }
            meeting.setUsers(users);
        }
    }
}
