package app.observer;

import app.warehouse.entity.Item;
import app.warehouse.events.StoreItemEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.beans.PropertyChangeEvent;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class HazardAlarmTest {

    @Mock
    private PrintStream mockPrintStream;
    @Mock
    private EventHandler mockEventHandler;

    private HazardAlarm hazardAlarmUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        hazardAlarmUnderTest = new HazardAlarm(mockPrintStream, mockEventHandler);
    }

    @Test
    void testPropertyChange() {
        final PropertyChangeEvent evt = new PropertyChangeEvent("o", "s", "o1", "o2");

        hazardAlarmUnderTest.propertyChange(evt);

        verify(mockPrintStream, never()).println(anyString());
    }

    @Test
    void testGetSubscribedEvents() {

        final ArrayList<SubscriberContainerInterface> result = hazardAlarmUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), StoreItemEvent.class);

    }

    @Test
    void testUpdate() {
        final StoreItemEvent event = mock(StoreItemEvent.class);
        final Item item = mock(Item.class);
        when(item.getHazards()).thenReturn(new ArrayList<>());
        when(event.getItem()).thenReturn(item);

        final Event result = hazardAlarmUnderTest.update(event);

    }

    @Test
    void testGetName() {
        final String result = hazardAlarmUnderTest.getName();

        assertEquals("observer:HazardAlarm", result);
    }
    @Test
    void testSetActive() {
        hazardAlarmUnderTest.setActive(false);

        assertFalse(hazardAlarmUnderTest.isActive());
    }
}
