package com.example.mareu.repo;

import com.example.mareu.factory.UserRepository;
import com.example.mareu.model.User;
import com.example.mareu.service.RoomApiService;
import com.example.mareu.service.UserApiService;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    private UserApiService api;
    private UserRepository repository;


    @Test
    public void getUser() {
        // GIVEN
        User fakeUser = new User(6,"mail");
        when(api.getUser(anyInt())).thenReturn(fakeUser);

        // WHEN
        User result = repository.getUser(6);

        //THEN
        assertEquals(result.getId(), 6);
        assertEquals(result.getMail(), "mail");
    }

    @Test
    public void getUsers() {
        // GIVEN
        User fakeUser1 = new User(6,"mail1");
        User fakeUser2 = new User(2,"mail2");
        List<User> users = new ArrayList<>();
        users.add(fakeUser1);
        users.add(fakeUser2);
        when(api.getUsers()).thenReturn(users);

        // WHEN
        List<User> result = repository.getUsers();

        //THEN
        assertEquals(result.size(), 2);
        assertEquals(result.get(1).getId(), 2);


    }

    @Before
    public void init(){
        api = mock(UserApiService.class);
        repository = new UserRepositoryFake(api);
    }

    public class UserRepositoryFake extends UserRepository {

        public UserRepositoryFake(UserApiService api) {
            super(api);
        }
    }
}
