package com.example.mareu;

import com.example.mareu.model.User;
import com.example.mareu.service.impl.UserServiceImpl;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserApiServiceImplTest {

    UserServiceImpl impl = new UserServiceImpl();

    @Test
    public void getUser() {
        // GIVEN
        User user;

        // WHEN
        user = impl.getUser(1);

        // THEN
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    public void getUsers() {
        // GIVEN

    }
}
