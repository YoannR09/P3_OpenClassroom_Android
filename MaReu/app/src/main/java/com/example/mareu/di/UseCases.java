package com.example.mareu.di;

import com.example.mareu.useCases.meetings.DeleteMeetingUseCase;
import com.example.mareu.useCases.meetings.FilterMeetingsByHourUseCase;
import com.example.mareu.useCases.meetings.FilterMeetingsByRoomAndHourUseCase;
import com.example.mareu.useCases.meetings.FilterMeetingsByRoomUseCase;
import com.example.mareu.useCases.meetings.GetMeetingByIdUseCase;
import com.example.mareu.useCases.meetings.GetMeetingByRoomIdUseCase;
import com.example.mareu.useCases.meetings.GetMeetingsUseCase;
import com.example.mareu.useCases.rooms.GetRoomsUseCase;
import com.example.mareu.useCases.users.GetUsersUseCase;

public class UseCases {

    private static GetMeetingsUseCase getMeetingsUseCase;
    private static GetMeetingByIdUseCase getMeetingByIdUseCase;
    private static GetMeetingByRoomIdUseCase getMeetingByRoomIdUseCase;
    private static GetRoomsUseCase getRoomsUseCase;
    private static GetUsersUseCase getUsersUseCase;
    private static DeleteMeetingUseCase deleteMeetingUseCase;
    private static FilterMeetingsByHourUseCase filterMeetingsByHourUseCase;
    private static FilterMeetingsByRoomAndHourUseCase filterMeetingsByRoomAndHourCase;
    private static FilterMeetingsByRoomUseCase filterMeetingsByRoomUseCase;

    public static GetMeetingsUseCase getMeetingsUseCase() {
        if(getMeetingsUseCase == null) getMeetingsUseCase = new GetMeetingsUseCase();
        return getMeetingsUseCase;
    }

    public static GetMeetingByIdUseCase getGetMeetingByIdUseCase() {
        if(getMeetingByIdUseCase == null) getMeetingByIdUseCase = new GetMeetingByIdUseCase();
        return getMeetingByIdUseCase;
    }

    public static GetMeetingByRoomIdUseCase getGetMeetingByRoomIdUseCase() {
        if(getMeetingByRoomIdUseCase == null) getMeetingByRoomIdUseCase = new GetMeetingByRoomIdUseCase();
        return getMeetingByRoomIdUseCase;
    }

    public static GetRoomsUseCase getRoomsUseCase() {
        if(getRoomsUseCase == null) getRoomsUseCase = new GetRoomsUseCase();
        return getRoomsUseCase;
    }

    public static GetUsersUseCase getUsersUseCase() {
        if(getUsersUseCase == null) getUsersUseCase = new GetUsersUseCase();
        return getUsersUseCase;
    }

    public static DeleteMeetingUseCase deleteMeetingUseCase() {
        if(deleteMeetingUseCase == null) deleteMeetingUseCase = new DeleteMeetingUseCase();
        return deleteMeetingUseCase;
    }

    public static FilterMeetingsByRoomAndHourUseCase filterMeetingsByRoomAndHourUseCase() {
        if(filterMeetingsByRoomAndHourCase == null)
            filterMeetingsByRoomAndHourCase = new FilterMeetingsByRoomAndHourUseCase();
        return filterMeetingsByRoomAndHourCase;
    }

    public static FilterMeetingsByHourUseCase filterMeetingsByHourUseCase() {
        if(filterMeetingsByHourUseCase == null)
            filterMeetingsByHourUseCase = new FilterMeetingsByHourUseCase();
        return filterMeetingsByHourUseCase;
    }

    public static  FilterMeetingsByRoomUseCase filterMeetingsByRoomUseCase() {
        if(filterMeetingsByRoomUseCase == null)
            filterMeetingsByRoomUseCase = new FilterMeetingsByRoomUseCase();
        return filterMeetingsByRoomUseCase;
    }
}
