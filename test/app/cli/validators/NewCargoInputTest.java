package app.cli.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewCargoInputTest {

    private NewCargoInput newCargoInputUnderTest;

    @BeforeEach
    void setUp() {
        newCargoInputUnderTest = new NewCargoInput();
    }

    @Test
    void testIsValid() {



        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"MixedCargoLiquidBulkAndUnitised", "Beispielkunde" ,"40.50" ,"86400", "radioactive" ,"n" ,"y","y"});


        assertTrue(result);
    }

    @Test
    void testIsValidThrowsExceptionItem() {

        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"", "Beispielkunde" ,"23.30" ,"86400", "radioactive" ,"y" ,"y","-"});
        assertFalse(result);
    }
    @Test
    void testIsValidThrowsExceptionWagge() {

        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"MixedCargoLiquidBulkAndUnitised", "Beispielkunde" ,"No Number" ,"86400", "radioactive" ,"n" ,"y","y"});
        assertFalse(result);
        assertEquals( "Gewicht als Zahl eingeben!",newCargoInputUnderTest.getMessage());
    }
    @Test
    void testIsValidThrowsExceptionTime() {

        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"MixedCargoLiquidBulkAndUnitised", "Beispielkunde" ,"23.30" ,"ASAS", "radioactive" ,"n" ,"y","y"});
        assertFalse(result);
        assertEquals( "Einlagerungsdauer als Zahl eingeben!",newCargoInputUnderTest.getMessage());
    }
    @Test
    void testIsValidThrowsExceptionPressure() {

        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"MixedCargoLiquidBulkAndUnitised", "Beispielkunde" ,"23.30" ,"86400", "radioactive" ,"-" ,"y","y"});
        assertTrue(result);
        assertEquals("Bitte y oder n angeben für den Zustand der Zerbrechlichkeit",newCargoInputUnderTest.getMessage());
    }
    @Test
    void testIsValidThrowsExceptionSolid() {

        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"MixedCargoLiquidBulkAndUnitised", "Beispielkunde" ,"23.30" ,"86400", "radioactive" ,"-" ,"y","y"});
        assertTrue(result);
        assertEquals(  "Bitte y oder n angeben für den Zustand der Zerbrechlichkeit",newCargoInputUnderTest.getMessage());
    }
    @Test
    void testIsValidThrowsExceptionFragile() {

        final Boolean result = newCargoInputUnderTest.isValid(new String[]{"MixedCargoLiquidBulkAndUnitised", "Beispielkunde" ,"23.30" ,"86400", "radioactive" ,"y" ,"y","-"});
        assertTrue(result);
        assertEquals( "Bitte y oder n angeben für den Zustand der Zerbrechlichkeit", newCargoInputUnderTest.getMessage());
    }
    @Test
    void testIsNotValid() {



        final Boolean result = newCargoInputUnderTest.isValid(new String[]{""});


        assertFalse(result);
    }

    @Test
    void testGetMessage() {
        final String result = newCargoInputUnderTest.getMessage();

        assertNull(result);
    }
}
