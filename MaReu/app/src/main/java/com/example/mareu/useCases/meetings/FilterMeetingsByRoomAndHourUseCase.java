package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Meeting;

import java.util.List;

public class FilterMeetingsByRoomAndHourUseCase {

    public List<Meeting> call(int hour, int roomId) {
        return Repositories.getMeetingRepository().filterByHourAndRoom(hour, roomId);
    }
}
