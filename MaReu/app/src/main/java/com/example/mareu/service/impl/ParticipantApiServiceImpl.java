package com.example.mareu.service.impl;

import com.example.mareu.model.Participant;
import com.example.mareu.model.Room;
import com.example.mareu.service.ParticipantApiService;
import com.example.mareu.service.generator.ParticipantGenerator;
import com.example.mareu.service.generator.RoomGenerator;

import java.util.ArrayList;
import java.util.List;

public class ParticipantApiServiceImpl implements ParticipantApiService {

    private List<Participant> participants = ParticipantGenerator.generateParticipants();

    @Override
    public List<Participant> getParticipantByMeetingId(int id) {
        List<Participant> vList = new ArrayList<>();
        for(Participant participant: participants) {
            if (participant.getMeetingId() == id) {
                vList.add(participant);
            }
        }
        return vList;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
}
