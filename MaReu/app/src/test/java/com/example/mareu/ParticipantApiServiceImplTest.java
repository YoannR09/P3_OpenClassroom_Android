package com.example.mareu;

import com.example.mareu.model.Participant;
import com.example.mareu.service.impl.ParticipantApiServiceImpl;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParticipantApiServiceImplTest {

    ParticipantApiServiceImpl impl = new ParticipantApiServiceImpl();

    @Test
    public void getParticipantByMeetingId() {
        // GIVEN
        List<Participant> vList;

        // WHEN
        vList = impl.getParticipantByMeetingId(1);

        // THEN
        assertEquals(3, vList.size());
        assertNotNull(vList);
    }

    @Test
    public void addParticipant() {

        // GIVEN
        int meetingId = 14;
        Participant participant = new Participant(2,meetingId);

        // WHEN
        impl.addParticipant(participant);

        // THEN

        assertNotNull(impl.getParticipantByMeetingId(meetingId));
        assertEquals(1,impl.getParticipantByMeetingId(meetingId).size());

    }
}
