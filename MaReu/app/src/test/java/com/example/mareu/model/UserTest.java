package com.example.mareu.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserTest {

    User user = new User(0,null);

    @Test
    public void setterAndGetterId() {
        user.setId(5);
        int id = user.getId();
        assertEquals(id, 5);
    }

    @Test
    public void setterAndGetterMail() {
        user.setMail("mail");
        String mail = user.getMail();
        assertEquals(mail, "mail");
    }
}
