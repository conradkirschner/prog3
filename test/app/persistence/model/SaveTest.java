package app.persistence.model;

import app.persistence.Persistence;
import app.persistence.events.SaveApplicationEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class SaveTest {

    private Save saveUnderTest;

    @Mock
    SaveApplicationEvent mockSaveApplicationEvent;

    @BeforeEach
    void setUp() {
        initMocks(this);
        saveUnderTest = new Save();
        saveUnderTest.persistence = mock(Persistence.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = saveUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), SaveApplicationEvent.class);


    }

    @Test
    void testUpdate() {
        final Event event = mockSaveApplicationEvent;
        when(mockSaveApplicationEvent.getType()).thenReturn("JOS");

        final Event result = saveUnderTest.update(event);

        verify(saveUnderTest.persistence).saveAsJOS();
    }

    @Test
    void testGetName() {
        final String result = saveUnderTest.getName();

        assertEquals("persistence:Save", result);
    }

    @Test
    void testSetActive() {
        saveUnderTest.setActive(false);

        assertFalse(saveUnderTest.isActive());
    }
}
