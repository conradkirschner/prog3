package app.user.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserEventTest {

    private CreateUserEvent createUserEventUnderTest;

    @BeforeEach
    void setUp() {
        createUserEventUnderTest = new CreateUserEvent("username", false);
    }

    @Test
    void testGetName() {



        final String result = createUserEventUnderTest.getName();


        assertEquals(CreateUserEvent.class.getName(), result);
    }

    @Test
    void testSetName() {
        createUserEventUnderTest.setName("test-name");

        assertEquals("test-name", createUserEventUnderTest.getName());
    }
    @Test
    void testSetUsername() {
        createUserEventUnderTest.setUsername("test-name");

        assertEquals("test-name", createUserEventUnderTest.getUsername());
    }
    @Test
    void testGetStatus() {
        createUserEventUnderTest.setStatus(true);

        assertEquals(true, createUserEventUnderTest.getStatus());
    }
}
