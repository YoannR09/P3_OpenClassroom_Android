package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Meeting;

public class DeleteMeetingUseCase {

    public void delete(Meeting meeting) {
        Repositories.getMeetingRepository().deleteMeeting(meeting);
    }
}
