package app.cli.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShowScreenEventTest {

    private ShowScreenEvent showScreenEventUnderTest;

    @BeforeEach
    void setUp() {
        showScreenEventUnderTest = new ShowScreenEvent();
    }

    @Test
    void testGetName() {
        final String result = showScreenEventUnderTest.getName();

        assertEquals(ShowScreenEvent.class.getName(), result);
    }
}
