package app.gui.events;

import famework.event.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

class GUIStartEventTest {

    @Mock
    private EventHandler mockEventHandler;

    private GUIStartEvent guiStartEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        guiStartEventUnderTest = new GUIStartEvent(mockEventHandler);
    }

    @Test
    void testGetName() {

        final String result = guiStartEventUnderTest.getName();

        assertEquals(GUIStartEvent.class.getName(), result);
    }
    @Test
    void testGetEventHandler() {
        final EventHandler result = guiStartEventUnderTest.getEventHandler();

        assertEquals(mockEventHandler, result);
    }
    @Test
    void testGetEventHandlerNull() {
        guiStartEventUnderTest = new GUIStartEvent();

        final EventHandler result = guiStartEventUnderTest.getEventHandler();

        assertNull(result);
    }
}
