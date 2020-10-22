package app.persistence.model;

import app.persistence.Persistence;
import app.persistence.events.LoadApplicationEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class LoadTest {

    private Load loadUnderTest;

    @BeforeEach
    void setUp() {
        loadUnderTest = new Load();
        loadUnderTest.persistence = mock(Persistence.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = loadUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), LoadApplicationEvent.class);
    }

    @Test
    void testUpdateNoSavepoints() {
        final Event event = null;
        when(loadUnderTest.persistence.loadFromJOS()).thenReturn(false);

        final Event result = loadUnderTest.update(event);

        verify(loadUnderTest.persistence, never()).loadFromJOS();
    }

    @Test
    void testUpdateSuccess() {
        final LoadApplicationEvent event = mock(LoadApplicationEvent.class);
        when(loadUnderTest.persistence.loadFromJOS()).thenReturn(false);
        when(event.getType()).thenReturn("JOS");

        final Event result = loadUnderTest.update(event);

        verify(loadUnderTest.persistence).loadFromJOS();
    }

    @Test
    void testGetName() {
        final String result = loadUnderTest.getName();

        assertEquals("persistance:Load", result);
    }
    @Test
    void testSetActive() {
        loadUnderTest.setActive(false);

        assertFalse(loadUnderTest.isActive());
    }
}
