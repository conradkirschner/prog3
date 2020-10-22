package app.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserListTest {

    private UserList userListUnderTest;

    @BeforeEach
    void setUp() {
        userListUnderTest = new UserList();
    }

    @Test
    void testToString() {
        userListUnderTest.setUserList(new ArrayList<>());

        final String result = userListUnderTest.toString();

        assertEquals("[]", result);
    }
    @Test
    void testGetUserList() {
        ArrayList<User> userList = new ArrayList<>();
        userListUnderTest.setUserList(userList);

        final ArrayList<User> result = (ArrayList<User>) userListUnderTest.getUserList();

        assertEquals(userList, result);
    }
}
