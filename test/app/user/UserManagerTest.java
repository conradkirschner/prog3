package app.user;

import app.user.entity.User;
import famework.configReader.ConfigBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserManagerTest {

    @Mock
    private ConfigBag mockTest;

    private UserManager userManagerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userManagerUnderTest = new UserManager(mockTest);
    }

    @Test
    void testGetUser() {
        final ArrayList<User> result = userManagerUnderTest.getUser();

    }

    @Test
    void testGetUserByIdFound() {
        final User user = new User("username");
        userManagerUnderTest.newUser(user);

        final ArrayList<User> result = userManagerUnderTest.getUser("username");

        assertEquals(user, result.get(0));
    }

    @Test
    void testGetUserByIdNotFound() {
        final ArrayList<User> result = userManagerUnderTest.getUser("username");


        assertNull(result);
    }

    @Test
    void testNewUser() {
        final User user = new User("username");

        userManagerUnderTest.newUser(user);

    }

    @Test
    void testRemoveUser() {
        final User user = new User("username");
        userManagerUnderTest.newUser(user);

        final boolean result = userManagerUnderTest.removeUser("username");

        assertTrue(result);
    }

    @Test
    void testRemoveUserFailure() {
        final boolean result = userManagerUnderTest.removeUser("username");

        assertFalse(result);
    }
}
