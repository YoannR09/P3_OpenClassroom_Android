package com.example.mareu.useCases.meetings;

import com.example.mareu.di.Repositories;
import com.example.mareu.model.Meeting;

import java.util.List;

public class FilterMeetingsByHourUseCase {

    public List<Meeting> call(int hour) {
        return Repositories.getMeetingRepository().filterByHour(hour);
    }
}
