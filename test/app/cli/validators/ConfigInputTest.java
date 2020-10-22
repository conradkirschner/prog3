package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigInputTest {

    private ConfigInput configInputUnderTest;

    @BeforeEach
    void setUp() {
        configInputUnderTest = new ConfigInput();
    }

    @Test
    void testIsValidFailure() {

        final Boolean result = configInputUnderTest.isValid("input");

        assertFalse(result);
    }
    @Test
    void testIsValidStorage() {
        final Boolean result = configInputUnderTest.isValid("storage 1");

        assertTrue(result);
    }
    @Test
    void testIsValidActivate() {
        final Boolean result = configInputUnderTest.isValid("activate 1");

        assertTrue(result);
    }
    @Test
    void testIsValidDeactivate() {
        final Boolean result = configInputUnderTest.isValid("deactivate 1");

        assertTrue(result);
    }

    @Test
    void testIsValidPlace() {
        final Boolean result = configInputUnderTest.isValid("place test 2 2");

        assertTrue(result);
    }
    @Test
    void testIsValids() {
        final Boolean result = configInputUnderTest.isValid("place test 2 2".split(" "));

        assertTrue(result);
    }

    @Test
    void testGetType() {
        configInputUnderTest.setType("test");
        final String result = configInputUnderTest.getType();

        assertEquals("test",result);
    }
    @Test
    void testGetError() {
        configInputUnderTest.setError("test");
        final String result = configInputUnderTest.getError();

        assertEquals("test",result);
    }
    @Test
    void testGetStorageName() {
        configInputUnderTest.setStorageName("test");
        final String result = configInputUnderTest.getStorageName();

        assertEquals("test",result);
    }
    @Test
    void testGetStorageNames() {
        String[] actual = new String[1];
        actual[0] = "2";

        configInputUnderTest.isValid("storage 2 3 4 5");
        final String[] result = configInputUnderTest.getStorageNames();

        assertEquals(actual[0],result[0]);
    }
    @Test
    void testGetSize() {
        configInputUnderTest.setSize(1);
        final int result = configInputUnderTest.getSize();

        assertEquals(1,result);
    }


    @Test
    void testGetMessage() {
        configInputUnderTest.setError("error");

        final String result = configInputUnderTest.getMessage();

        assertEquals("error", result);
    }
    @Test
    void testGetStorageNumber() {
        configInputUnderTest.setError("error");
        configInputUnderTest.isValid("place test 2 2");

        final int result = configInputUnderTest.getStorageNumber();

        assertEquals(2, result);
    }
}
