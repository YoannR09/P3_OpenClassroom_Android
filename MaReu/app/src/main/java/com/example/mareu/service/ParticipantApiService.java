package com.example.mareu.service;

import com.example.mareu.model.Participant;

import java.util.List;

public interface ParticipantApiService {
    List<Participant> getParticipantByMeetingId(int id);

    void addParticipant(Participant participant);
}
