package com.example.mareu.service.impl;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.generator.MeetingGenerator;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiServiceImpl implements MeetingApiService {

    private List<Meeting> meetings = MeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByRoomId(int roomId) {
        List<Meeting> vList = new ArrayList<>();
        for(Meeting m: meetings) {
            if(m.getRoomId() == roomId) {
                vList.add(m);
            }
        }
        return vList;
    }

    @Override
    public Meeting getMeeting(int id) {
        Meeting mToReturn = null;
        for(Meeting m: meetings) {
            if (m.getId() == id){
                mToReturn = m;
            }
        }
        return mToReturn;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
