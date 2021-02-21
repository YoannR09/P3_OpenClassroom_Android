package com.example.mareu.di;

import com.example.mareu.factory.MeetingRepository;
import com.example.mareu.factory.ParticipantRepository;
import com.example.mareu.factory.RoomRepository;
import com.example.mareu.factory.UserRepository;
import com.example.mareu.service.impl.MeetingApiServiceImpl;
import com.example.mareu.service.impl.ParticipantApiServiceImpl;
import com.example.mareu.service.impl.RoomApiServiceImpl;
import com.example.mareu.service.impl.UserServiceImpl;

public class Repositories {

    private static MeetingRepository meetingRepository;
    private static RoomRepository roomRepository;
    private static UserRepository userRepository;
    private static ParticipantRepository participantRepository;

    public static RoomRepository getRoomRepository() {
        if(roomRepository == null) roomRepository = Repositories.createRoomRepository();
        return roomRepository;
    }

    public static MeetingRepository getMeetingRepository() {
        if(meetingRepository == null) meetingRepository = Repositories.createMeetingRepository();
        return meetingRepository;
    }

    public static UserRepository getUserRepository() {
        if(userRepository == null) userRepository = Repositories.createUserRepository();
        return userRepository;
    }

    public static ParticipantRepository getParticipantRepository() {
        if(participantRepository == null) participantRepository = Repositories.createParticipantRepository();
        return participantRepository;
    }

    public static MeetingRepository createMeetingRepository() {
        return new MeetingRepository(new MeetingApiServiceImpl());
    }

    public static RoomRepository createRoomRepository() {
        return new RoomRepository(new RoomApiServiceImpl());
    }

    public static UserRepository createUserRepository() {
        return new UserRepository(new UserServiceImpl());
    }

    public static ParticipantRepository createParticipantRepository() {
        return new ParticipantRepository(new ParticipantApiServiceImpl());
    }
}
