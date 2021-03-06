package com.example.mareu.service.generator;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingGenerator {

    public static List<Meeting> MEETINGS = Arrays.asList(
            new Meeting(1,"Test", 1, 12, 0),
            new Meeting(2,"Sujet", 2, 13,30)
    );

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }
}
