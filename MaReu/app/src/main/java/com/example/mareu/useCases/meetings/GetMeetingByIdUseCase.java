package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Meeting;

public class GetMeetingByIdUseCase {

    public Meeting call(int id) {
        return Repositories.getMeetingRepository().getMeeting(id);
    }
}
