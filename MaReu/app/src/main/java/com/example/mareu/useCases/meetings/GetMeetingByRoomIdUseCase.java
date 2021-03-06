package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Meeting;

import java.util.List;

public class GetMeetingByRoomIdUseCase {

    public List<Meeting> call(int roomId) {
        return Repositories.getMeetingRepository().getMeetingsByRoomId(roomId);
    }
}
