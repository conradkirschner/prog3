package app.cli.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToggleActiveStateEventTest {

    private ToggleActiveStateEvent toggleActiveStateEventUnderTest;

    @BeforeEach
    void setUp() {
        toggleActiveStateEventUnderTest = new ToggleActiveStateEvent(true, new String[]{"value"});
    }

    @Test
    void testGetName() {
        final String result = toggleActiveStateEventUnderTest.getName();

        assertEquals("app.cli.events.ToggleActiveStateEvent", result);
    }
    @Test
    void testGetModifySubscriber() {
        final String[] result = toggleActiveStateEventUnderTest.getModifySubscriber();

        boolean found = false;
        for (String string: result) {
            if (string.equals("value")) {
                found = true;

            }
        }
        assertTrue(found);
    }
    @Test
    void testGetChangeTo() {
        final boolean result = toggleActiveStateEventUnderTest.getChangeTo();

        assertTrue(result);
    }
    @Test
    void testEmptyConstructor() {
        final ToggleActiveStateEvent result = new ToggleActiveStateEvent();

        assertNotEquals(result, toggleActiveStateEventUnderTest);
    }
}
