package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateInputTest {

    private UpdateInput updateInputUnderTest;

    @BeforeEach
    void setUp() {
        updateInputUnderTest = new UpdateInput();
    }

    @Test
    void testIsNotValid() {
        final Boolean result = updateInputUnderTest.isValid(new String[]{"value"});

        assertFalse(result);
    }

    @Test
    void testIsValid() {
        final Boolean result = updateInputUnderTest.isValid(new String[]{"#123123"});

        assertTrue(result);
    }
    @Test
    void testGetId() {
        updateInputUnderTest.isValid(new String[]{"#123123"});
        String id = updateInputUnderTest.getId();
        assertEquals("#123123" , id);
    }

    @Test
    void testGetMessage() {
        final String result = updateInputUnderTest.getMessage();

        assertNull(result);
    }
}
