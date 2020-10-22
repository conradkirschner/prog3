package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceInputTest {

    private PersistenceInput persistenceInputUnderTest;

    @BeforeEach
    void setUp() {
        persistenceInputUnderTest = new PersistenceInput();
    }

    @Test
    void testIsNotValid() {
        final Boolean result = persistenceInputUnderTest.isValid(new String[]{"value"});

        assertFalse(result);
    }

    @Test
    void testGetMessage() {
        final String result = persistenceInputUnderTest.getMessage();

        assertNull(result);
    }
    @Test
    void testGetTypeJOS() {
        persistenceInputUnderTest.isValid(new String[]{"y"});

        final String result = persistenceInputUnderTest.getType();

        assertEquals("JOS",result);
    }
}
