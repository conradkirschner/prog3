package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchInputTest {

    private SearchInput searchInputUnderTest;

    @BeforeEach
    void setUp() {
        searchInputUnderTest = new SearchInput();
    }

    @Test
    void testIsNotValid() {
        final Boolean result = searchInputUnderTest.isValid(new String[]{"value"});

        assertFalse(result);
    }

    @Test
    void testGetMessage() {
        final String result = searchInputUnderTest.getMessage();

        assertNull(result);
    }
    @Test
    void testIsValidCustomer() {
        final Boolean result = searchInputUnderTest.isValid(new String[]{"customer"});
        assertTrue(result);
    }
    @Test
    void testIsValidCargo() {
        final Boolean result = searchInputUnderTest.isValid(new String[]{"cargo"});
        assertFalse(result);
    }
    @Test
    void testIsValidHazard() {
        final Boolean result = searchInputUnderTest.isValid(new String[]{"hazard"});
        assertFalse(result);
    }
    @Test
    void testGetTypeCustomer() {
        searchInputUnderTest.isValid(new String[]{"customer"});
        final String type = searchInputUnderTest.getType();
        assertEquals("customer", type);
    }
    @Test
    void testGetTypeCargo() {
        searchInputUnderTest.isValid(new String[]{"cargo","arg2"});
        final String type = searchInputUnderTest.getType();
        final String arg2 = searchInputUnderTest.getArg2();
        assertEquals("cargo", type);
        assertEquals("arg2", arg2);
    }
    @Test
    void testGetTypeHazardFilterYes() {
        searchInputUnderTest.isValid(new String[]{"hazard","y"});
        final String type = searchInputUnderTest.getType();
        final boolean hazardFilter = searchInputUnderTest.getHazardFilter();
        assertEquals("hazard", type);
        assertTrue(hazardFilter);
    }
    @Test
    void testGetTypeHazardFilterNo() {
        searchInputUnderTest.isValid(new String[]{"hazard","n"});
        final String type = searchInputUnderTest.getType();
        final boolean hazardFilter = searchInputUnderTest.getHazardFilter();
        assertEquals("hazard", type);
        assertFalse(hazardFilter);
    }
}
