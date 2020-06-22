package app.persistence.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LoadApplicationEventTest {

    private LoadApplicationEvent loadApplicationEventUnderTest;

    @BeforeEach
    void setUp() {
        loadApplicationEventUnderTest = new LoadApplicationEvent(false);
    }

    @Test
    void testGetName() {
        final String result = loadApplicationEventUnderTest.getName();
        assertEquals(LoadApplicationEvent.class.getName(), result);
    }
    @Test
    void testGetStatus() {
        final Boolean result = loadApplicationEventUnderTest.getStatus();
        assertFalse(result);
    }
    @Test
    void testGetType() {
        loadApplicationEventUnderTest = new LoadApplicationEvent("type");
        final String result = loadApplicationEventUnderTest.getType();
        assertEquals("type", result);
    }
}
