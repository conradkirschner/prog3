package app.user.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteUserEventTest {

    private DeleteUserEvent deleteUserEventUnderTest;

    @BeforeEach
    void setUp() {
        deleteUserEventUnderTest = new DeleteUserEvent(false);
    }

    @Test
    void testGetName() {
        final String result = deleteUserEventUnderTest.getName();

        assertEquals(DeleteUserEvent.class.getName(), result);
    }
    @Test
    void testGetUsername() {
        deleteUserEventUnderTest = new DeleteUserEvent("test-Name");

        final String result = deleteUserEventUnderTest.getUsername();

        assertEquals("test-Name", result);
    }
}
