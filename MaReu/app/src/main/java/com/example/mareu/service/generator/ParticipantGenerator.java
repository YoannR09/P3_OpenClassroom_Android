package com.example.mareu.service.generator;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ParticipantGenerator {

    public static List<Participant> PARTICIPANTS = Arrays.asList(
            new Participant(1,1),
            new Participant(2,1),
            new Participant(3,1),
            new Participant(1,2),
            new Participant(4,2),
            new Participant(5,2),
            new Participant(2, 2)
    );

    public static List<Participant> generateParticipants() {
        return new ArrayList<>(PARTICIPANTS);
    }
}
