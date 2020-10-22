package app.gui.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private User userUnderTest;

    @BeforeEach
    void setUp() {
        userUnderTest = new User("name");
    }

    @Test
    void testGetUsername() {
        assertEquals("name", userUnderTest.getUsername());
    }
    @Test
    void testGetUsernameAfterChange() {
        userUnderTest.setUsername("NoName");
        assertEquals("NoName", userUnderTest.getUsername());
    }
}
