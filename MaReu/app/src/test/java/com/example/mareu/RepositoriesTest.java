package com.example.mareu;

import com.example.mareu.di.Repositories;
import com.example.mareu.factory.MeetingRepository;
import com.example.mareu.factory.ParticipantRepository;
import com.example.mareu.factory.RoomRepository;
import com.example.mareu.factory.UserRepository;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RepositoriesTest {

    Repositories repo = new Repositories();

    @Test
    public void  getRoomRepository() {
        // GIVEN
        RoomRepository repository;
        // WHEN
        repository = repo.getRoomRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void getMeetingRepository() {
        // GIVEN
        MeetingRepository repository;
        // WHEN
        repository = repo.getMeetingRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void getUserRepository() {
        // GIVEN
        UserRepository repository;
        // WHEN
        repository = repo.getUserRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void getParticipantRepository() {
        // GIVEN
        ParticipantRepository repository;
        // WHEN
        repository = repo.getParticipantRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void createMeetingRepository() {
        // GIVEN
        MeetingRepository repository;
        // WHEN
        repository = repo.createMeetingRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void createRoomRepository() {
        // GIVEN
        RoomRepository repository;
        // WHEN
        repository = repo.createRoomRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void createUserRepository() {
        // GIVEN
        UserRepository repository;
        // WHEN
        repository = repo.createUserRepository();
        // THEN
        assertNotNull(repository);
    }

    @Test
    public void createParticipantRepository() {
        // GIVEN
        ParticipantRepository repository;
        // WHEN
        repository = repo.createParticipantRepository();
        // THEN
        assertNotNull(repository);
    }
}
