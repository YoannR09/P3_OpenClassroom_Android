package com.example.mareu.factory;

import com.example.mareu.di.Repositories;
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
        List<Meeting> vList = getApi().getMeetings();
        initList(vList);
        return vList;
    }

    public List<Meeting> getMeetingsByRoomId(int roomId) {
        List<Meeting> vList = getApi().getMeetingsByRoomId(roomId);
        initList(vList);
        return vList;
    }

    public Meeting getMeeting(int id) {
        return getApi().getMeeting(id);
    }

    public void deleteMeeting(Meeting meeting) {
        getApi().deleteMeeting(meeting);
    }

    public void addMeeting(Meeting meeting) {
        getApi().addMeeting(meeting);
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

    public List<Meeting> filterByHour(int hour) {
        return getApi().filterByHour(hour);
    }

    public List<Meeting> filterByRoom(int roomId) {
        return getApi().filterByRoom(roomId);
    }

    public List<Meeting> filterByHourAndRoom(int hour, int roomId) {
        return getApi().filterByHourAndRoom(hour,roomId);
    }

    protected MeetingApiService getApi() {
        return api;
    }
}
