package com.example.mareu.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ParticipantTest {

    Participant participant = new Participant(0,0);

    @Test
    public void setterAndGetterUserId() {
        participant.setUserId(3);
        int userId = participant.getUserId();
        assertEquals(3, userId);
    }

    @Test
    public void setterAndGetterMeetingId() {
        participant.setMeetingId(10);
        int meetingId = participant.getMeetingId();
        assertEquals(participant.getMeetingId(), 10);
    }
}
