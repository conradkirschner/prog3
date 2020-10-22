package app.persistence.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoadApplicationEventTest {

    private LoadApplicationEvent loadApplicationEventUnderTest;


    @Test
    void testGetName() {

        loadApplicationEventUnderTest = new LoadApplicationEvent();

        final String result = loadApplicationEventUnderTest.getName();


        assertEquals(LoadApplicationEvent.class.getName(), result);
    }
    @Test
    void testGetType() {

        loadApplicationEventUnderTest = new LoadApplicationEvent("customeString");


        final String result = loadApplicationEventUnderTest.getType();


        assertEquals("customeString", result);
    }
    @Test
    void testGetStatus() {

        loadApplicationEventUnderTest = new LoadApplicationEvent(true);


        final boolean result = loadApplicationEventUnderTest.getStatus();


        assertTrue(result);
    }
}
