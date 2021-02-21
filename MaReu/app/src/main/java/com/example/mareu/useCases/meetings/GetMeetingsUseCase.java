package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Meeting;

import java.util.List;

public class GetMeetingsUseCase {

    public List<Meeting> call() {
        return Repositories.getMeetingRepository().getMeetings();
    }
}
