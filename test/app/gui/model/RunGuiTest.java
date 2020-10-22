package app.gui.model;

import app.gui.Window;
import app.gui.events.GUIStartEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class RunGuiTest {

    @Mock
    private BufferedInputStream mockIn;
    @Mock
    private PrintStream mockPrintStream;

    private RunGui runGuiUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        runGuiUnderTest = new RunGui(mockIn, mockPrintStream);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = runGuiUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), GUIStartEvent.class);
    }

    @Test
    void testUpdate() throws IOException {
        final GUIStartEvent event = mock(GUIStartEvent.class);
        when(event.getEventHandler()).thenReturn(mock(EventHandler.class));
        Window mockedWindow = mock(Window.class);
        runGuiUnderTest.setWindow(mockedWindow);
        doNothing().when(mockedWindow).start(any(EventHandler.class));
        final Event result = runGuiUnderTest.update(event);

        assertNull(result);
    }

    @Test
    void testGetName() {



        final String result = runGuiUnderTest.getName();


        assertEquals("gui:RunGui", result);
    }
}
