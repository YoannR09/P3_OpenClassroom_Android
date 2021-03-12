package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.factory.MeetingRepository;
import com.example.mareu.model.Meeting;

public class GetMeetingByIdUseCase {

    public Meeting call(int id) {
        return getMeetingRepository().getMeeting(id);
    }

    protected MeetingRepository getMeetingRepository() {
        return Repositories.getMeetingRepository();
    }
}
