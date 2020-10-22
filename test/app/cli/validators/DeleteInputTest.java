package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteInputTest {

    private DeleteInput deleteInputUnderTest;

    @BeforeEach
    void setUp() {
        deleteInputUnderTest = new DeleteInput();
    }

    @Test
    void testIsValidFailure() {
        final Boolean result = deleteInputUnderTest.isValid(new String[]{""});

        assertFalse(result);
    }
    @Test
    void testIsValidUsername() {
        final Boolean result = deleteInputUnderTest.isValid(new String[]{"Moses"});

        assertTrue(result);
    }
    @Test
    void testIsValidWarehouses() {
        final Boolean result = deleteInputUnderTest.isValid(new String[]{"" +
                "#12345"});

        assertTrue(result);
    }
    @Test
    void testIsValidPosition() {
        final Boolean result = deleteInputUnderTest.isValid(new String[]{"" +
                "#12345"});

        assertEquals(deleteInputUnderTest.getPosition(), "#12345");
    }
    @Test
    void testIsValidUsernameValue() {
        final Boolean result = deleteInputUnderTest.isValid(new String[]{"" +
                "Moses"});

        assertEquals(deleteInputUnderTest.getUsername(), "Moses");
    }

    @Test
    void testGetMessage() {
        final String result = deleteInputUnderTest.getMessage();

        assertNull(result);
    }
}
