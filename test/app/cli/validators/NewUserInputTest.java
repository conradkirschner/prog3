package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewUserInputTest {

    private NewUserInput newUserInputUnderTest;

    @BeforeEach
    void setUp() {
        newUserInputUnderTest = new NewUserInput();
    }

    @Test
    void testIsValid() {



        final Boolean result = newUserInputUnderTest.isValid(new String[]{"value"});


        assertTrue(result);
    }

    @Test
    void testGetMessage() {



        final String result = newUserInputUnderTest.getMessage();


        assertNull(result);
    }

    @Test
    void testGet() {



      newUserInputUnderTest.isValid("test as".split(" "));
        final String result = newUserInputUnderTest.get();


        assertEquals("test", result);
    }
}
