package app.persistence.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SaveApplicationEventTest {

    private SaveApplicationEvent saveApplicationEventUnderTest;


    @Test
    void testGetName() {

        saveApplicationEventUnderTest = new SaveApplicationEvent();

        final String result = saveApplicationEventUnderTest.getName();


        assertEquals(SaveApplicationEvent.class.getName(), result);
    }
    @Test
    void testGetType() {

        saveApplicationEventUnderTest = new SaveApplicationEvent("test");


        final String result = saveApplicationEventUnderTest.getType();


        assertEquals("test", result);
    }
    @Test
    void testGetStatus() {

        saveApplicationEventUnderTest = new SaveApplicationEvent(true);


        final boolean result = saveApplicationEventUnderTest.getStatus();


        assertTrue(result);
    }
}
