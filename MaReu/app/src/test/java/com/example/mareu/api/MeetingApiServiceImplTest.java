package com.example.mareu.api;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.impl.MeetingApiServiceImpl;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MeetingApiServiceImplTest {

    MeetingApiServiceImpl impl = new MeetingApiServiceImpl();

    @Test
    public void getMeetings() {

        // GIVEN
        List<Meeting> vList;

        // WHEN
        vList = impl.getMeetings();

        // THEN
        assertNotNull(vList);
        assertEquals(2, vList.size());
    }

    @Test
    public void getMeetingsByRoomId() {
        // GIVEN
        int roomId = 1;
        List<Meeting> vList;

        // WHEN
        vList = impl.getMeetingsByRoomId(roomId);

        // THEN
        assertNotNull(vList);
        assertEquals(1, vList.size());
    }

    @Test
    public void getMeeting() {
        // GIVEN
        Meeting meeting;

        // WHEN
        meeting = impl.getMeeting(1);

        // THEN
        assertNotNull(meeting);
        assertEquals(1, meeting.getId());
    }

    @Test
    public void deleteMeeting() {
        // GIVEN
        Meeting meeting = impl.getMeetings().get(0);
        int oldSize = impl.getMeetings().size();

        // WHEN
        impl.deleteMeeting(meeting);

        // THEN
        assertEquals(oldSize - 1, impl.getMeetings().size());
    }

    @Test
    public void addMeeting() {
        // GIVEN
        Meeting meeting = new Meeting(9,"Sujet",2,22,20);
        int oldSize = impl.getMeetings().size();

        // WHEN
        impl.addMeeting(meeting);

        // THEN
        assertEquals(oldSize + 1, impl.getMeetings().size());
    }

    @Test
    public void filterByHour() {
        // GIVEN
        List<Meeting> result;

        // WHEN
        result = impl.filterByHour(12);

        // THEN
        assertEquals(result.get(0).getHour(), 12);
    }

    @Test
    public void filterByRoom() {
        // GIVEN
        List<Meeting> result;

        // WHEN
        result = impl.filterByRoom(2);

        // THEN
        assertEquals(result.get(0).getRoomId(), 2);
    }

    @Test
    public void filterByHourAndRoom() {
        // GIVEN
        List<Meeting> result;

        // WHEN
        result = impl.filterByHourAndRoom(13, 2);

        // THEN
        assertEquals(result.get(0).getRoomId(), 2);
        assertEquals(result.get(0).getHour(), 13);
    }
}
