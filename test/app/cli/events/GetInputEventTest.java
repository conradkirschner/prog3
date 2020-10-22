package app.cli.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetInputEventTest {

    private GetInputEvent getInputEventUnderTestEmpty;
    private GetInputEvent getInputEventUnderTestContentOnly;
    private GetInputEvent getInputEventUnderTestValdidated;

    @BeforeEach
    void setUp() {
        getInputEventUnderTestEmpty = new GetInputEvent();
        getInputEventUnderTestContentOnly = new GetInputEvent("content");
        getInputEventUnderTestValdidated = new GetInputEvent("content", true);
    }

    @Test
    void testGetNameEmpty() {
        final String result = getInputEventUnderTestEmpty.getName();

        assertEquals(GetInputEvent.class.getName(), result);
    }
    @Test
    void testGetNameContentOnly() {
        final String result = getInputEventUnderTestContentOnly.getName();

        assertEquals(GetInputEvent.class.getName(), result);
    }

    @Test
    void testGetNameValidated() {
        final String result = getInputEventUnderTestValdidated.getName();

        assertEquals(GetInputEvent.class.getName(), result);
    }
    @Test
    void testGetContentEmpty() {
        final String result = getInputEventUnderTestEmpty.getContent();

        assertNull(result);
    }
    @Test
    void testGetContentContentOnly() {
        final String result = getInputEventUnderTestContentOnly.getContent();

        assertEquals("content", result);
    }
    @Test
    void testGetContentValidated() {
        final String result = getInputEventUnderTestValdidated.getContent();

        assertEquals("content", result);
    }
    @Test
    void testIsValidatedEmpty() {
        final boolean result = getInputEventUnderTestEmpty.isValidated();

        assertFalse(result);
    }
    @Test
    void testIsValidatedContentOnly() {
        final boolean result = getInputEventUnderTestContentOnly.isValidated();

        assertFalse(result);
    }
    @Test
    void testIsValidatedValidated() {
        final boolean result = getInputEventUnderTestValdidated.isValidated();

        assertTrue(result);
    }
}
