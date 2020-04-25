package app;

import app.events.Event;
import app.events.Module;
import app.events.ModuleEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class EventStreamTest {

    @Mock
    private App mockApp;

    private EventStream eventStreamUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        eventStreamUnderTest = new EventStream(mockApp);
    }

    @Test
    void testDataConnector() {
        ModuleEvent event = Mockito.mock(ModuleEvent.class);

        eventStreamUnderTest.dataConnector(event);

        assertEquals(1, eventStreamUnderTest.getModuleEventListeners().size());
    }

    @Test
    void testPushDataEmpty() {
        final Event result = eventStreamUnderTest.pushData("command", "data");

        assertNull(result);
    }
    @Test
    void testPushDataWithReturn() throws IOException, ParseException {
        String command = "command-test";
        String data = "data-test";
        Event eventData = Mockito.mock(Event.class);
        ModuleEvent event = Mockito.mock(ModuleEvent.class);
        when(event.shouldRun()).thenReturn(true);
        when(event.shouldReturn()).thenReturn(true);
        when(event.runModuleEvent(command, data, mockApp, null)).thenReturn(eventData);
        eventStreamUnderTest.dataConnector(event);

         Event result = eventStreamUnderTest.pushData(command, data);

        assertEquals(eventData, result);
    }
}
