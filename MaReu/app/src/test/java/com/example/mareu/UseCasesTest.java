package com.example.mareu;

import com.example.mareu.di.UseCases;
import com.example.mareu.useCases.meetings.DeleteMeetingUseCase;
import com.example.mareu.useCases.meetings.GetMeetingByIdUseCase;
import com.example.mareu.useCases.meetings.GetMeetingByRoomIdUseCase;
import com.example.mareu.useCases.meetings.GetMeetingsUseCase;
import com.example.mareu.useCases.rooms.GetRoomsUseCase;
import com.example.mareu.useCases.users.GetUsersUseCase;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UseCasesTest {

    UseCases useCases = new UseCases();

    @Test
    public void getMeetingsUseCase() {
        // GIVEN
        GetMeetingsUseCase getMeetingsUseCase;

        // WHEN
        getMeetingsUseCase = useCases.getMeetingsUseCase();

        // THEN
        assertNotNull(getMeetingsUseCase);
    }

    @Test
    public void getGetMeetingByIdUseCase() {
        // GIVEN
        GetMeetingByIdUseCase getGetMeetingByIdUseCase;

        // WHEN
        getGetMeetingByIdUseCase = useCases.getGetMeetingByIdUseCase();

        // THEN
        assertNotNull(getGetMeetingByIdUseCase);
    }

    @Test
    public void getGetMeetingByRoomIdUseCase() {
        // GIVEN
        GetMeetingByRoomIdUseCase getGetMeetingByRoomIdUseCase;

        // WHEN
        getGetMeetingByRoomIdUseCase = useCases.getGetMeetingByRoomIdUseCase();

        // THEN
        assertNotNull(getGetMeetingByRoomIdUseCase);
    }

    @Test
    public void getRoomsUseCase() {
        // GIVEN
        GetRoomsUseCase getRoomsUseCase;

        // WHEN
        getRoomsUseCase = useCases.getRoomsUseCase();

        // THEN
        assertNotNull(getRoomsUseCase);
    }

    @Test
    public void getUsersUseCase() {
        // GIVEN
        GetUsersUseCase getUsersUseCase;

        // WHEN
        getUsersUseCase = useCases.getUsersUseCase();

        // THEN
        assertNotNull(getUsersUseCase);
    }

    @Test
    public void deleteMeetingUseCase() {
        // GIVEN
        DeleteMeetingUseCase deleteMeetingUseCase;

        // WHEN
        deleteMeetingUseCase = useCases.deleteMeetingUseCase();

        // THEN
        assertNotNull(deleteMeetingUseCase);
    }
}
