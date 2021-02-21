package com.example.mareu.factory;

import com.example.mareu.model.Participant;
import com.example.mareu.service.ParticipantApiService;

import java.util.List;

public class ParticipantRepository {

    private ParticipantApiService api;

    public ParticipantRepository(ParticipantApiService api) {
        this.api = api;
    }

    public List<Participant> getParticipantByMeetingId(int id) {
        return getApi().getParticipantByMeetingId(id);
    }

    public void addParticipant(Participant participant) {
        getApi().addParticipant(participant);
    }

    protected ParticipantApiService getApi() {
        return api;
    }
}
