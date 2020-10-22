package famework.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class EventRegistryTest {

    private EventRegistry eventRegistryUnderTest;

    @BeforeEach
    void setUp() {
        eventRegistryUnderTest = new EventRegistry();
    }

    @Test
    void testAddListener() {
        Listener mockListener1 = mock(Listener.class);
        Listener mockListener2 = mock(Listener.class);
        when(mockListener1.getPrio()).thenReturn(10);
        when(mockListener2.getPrio()).thenReturn(0);

        eventRegistryUnderTest.addListener(mockListener1);
        eventRegistryUnderTest.addListener(mockListener2);
        assertTrue(eventRegistryUnderTest.getListeners().get(1).equals(mockListener1));
    }

    @Test
    void testRemoveListener() {
        Listener mockListener = mock(Listener.class);
        eventRegistryUnderTest.addListener(mockListener);

        eventRegistryUnderTest.removeListener(mockListener);
        assertEquals(0, eventRegistryUnderTest.getListeners().size());

    }

    @Test
    void testPush() throws Exception {
        Event event = mock(Event.class);
        Listener mockListener = mock(Listener.class);
        eventRegistryUnderTest.addListener(mockListener);
        when(mockListener.update(event)).thenReturn(event);

        Event result = eventRegistryUnderTest.push(event);
        verify(mockListener,times(1)).update(event);
    }
}
