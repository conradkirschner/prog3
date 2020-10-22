package app.cli.model;

import app.cli.events.ToggleActiveStateEvent;
import famework.event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToggleActiveStateTest {

    private ToggleActiveState toggleActiveStateUnderTest;

    @BeforeEach
    void setUp() {
        toggleActiveStateUnderTest = new ToggleActiveState();
        toggleActiveStateUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = toggleActiveStateUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), ToggleActiveStateEvent.class);
    }

    @Test
    void testUpdateNoSubscriberOnListener() {
        final Event event = new ToggleActiveStateEvent(true, new String[]{"test"});
        ArrayList<Listener> listeners = new ArrayList<>();
        Listener listener = mock(Listener.class);
        listeners.add(listener);
        EventRegistry testRegistry = mock(EventRegistry.class);

        when(toggleActiveStateUnderTest.eventHandler.getRegistry()).thenReturn(testRegistry);
        when(testRegistry.getListeners()).thenReturn(listeners);
        when(listener.getSubscriber()).thenReturn(null);

        final Event result = toggleActiveStateUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateNoSubscriber() {
        final Event event = new ToggleActiveStateEvent(true, new String[]{"test"});
        ArrayList<Listener> listeners = new ArrayList<>();
        Listener listener = mock(Listener.class);
        listeners.add(listener);

        Subscriber subscriber = mock(Subscriber.class);

        EventRegistry testRegistry = mock(EventRegistry.class);

        when(toggleActiveStateUnderTest.eventHandler.getRegistry()).thenReturn(testRegistry);
        when(testRegistry.getListeners()).thenReturn(listeners);
        when(subscriber.getSubscribedEvents()).thenReturn(null);

        final Event result = toggleActiveStateUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateNoSubscriberEvents() {
        final Event event = new ToggleActiveStateEvent(true, new String[]{"test"});
        ArrayList<Listener> listeners = new ArrayList<>();
        Listener listener = mock(Listener.class);
        listeners.add(listener);

        Subscriber subscriber = mock(Subscriber.class);

        EventRegistry testRegistry = mock(EventRegistry.class);

        when(toggleActiveStateUnderTest.eventHandler.getRegistry()).thenReturn(testRegistry);
        when(testRegistry.getListeners()).thenReturn(listeners);
        when(listener.getSubscriber()).thenReturn(subscriber);
        when(subscriber.getSubscribedEvents()).thenReturn(null);

        final Event result = toggleActiveStateUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateNoEvent() {
        final Event event = new ToggleActiveStateEvent(true, new String[]{"test"});
        ArrayList<Listener> listeners = new ArrayList<>();
        Listener listener = mock(Listener.class);
        listeners.add(listener);

        Subscriber subscriber = mock(Subscriber.class);

        EventRegistry testRegistry = mock(EventRegistry.class);

        when(toggleActiveStateUnderTest.eventHandler.getRegistry()).thenReturn(testRegistry);
        when(testRegistry.getListeners()).thenReturn(listeners);
        when(listener.getSubscriber()).thenReturn(subscriber);

        final Event result = toggleActiveStateUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateNoEventName() {
        final Event event = new ToggleActiveStateEvent(true, new String[]{"test"});
        ArrayList<Listener> listeners = new ArrayList<>();
        Listener listener = mock(Listener.class);
        listeners.add(listener);

        Subscriber subscriber = mock(Subscriber.class);
        ArrayList<SubscriberContainerInterface> subscriberContainerInterfaces = new ArrayList<>();
        SubscriberContainerInterface subscriberContainerInterface = mock(SubscriberContainerInterface.class);
        subscriberContainerInterfaces.add(subscriberContainerInterface);

        EventRegistry testRegistry = mock(EventRegistry.class);

        when(toggleActiveStateUnderTest.eventHandler.getRegistry()).thenReturn(testRegistry);
        when(testRegistry.getListeners()).thenReturn(listeners);
        when(listener.getSubscriber()).thenReturn(subscriber);
        when(subscriber.getSubscribedEvents()).thenReturn(subscriberContainerInterfaces);

        final Event result = toggleActiveStateUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateSuccess() {
        final Event event = new ToggleActiveStateEvent(true, new String[]{"test"});
        ArrayList<Listener> listeners = new ArrayList<>();
        Listener listener = mock(Listener.class);
        listeners.add(listener);

        Subscriber subscriber = mock(Subscriber.class);
        ArrayList<SubscriberContainerInterface> subscriberContainerInterfaces = new ArrayList<>();
        SubscriberContainerInterface subscriberContainerInterface = mock(SubscriberContainerInterface.class);

        subscriberContainerInterfaces.add(subscriberContainerInterface);

        EventRegistry testRegistry = mock(EventRegistry.class);

        when(toggleActiveStateUnderTest.eventHandler.getRegistry()).thenReturn(testRegistry);
        when(testRegistry.getListeners()).thenReturn(listeners);
        when(listener.getSubscriber()).thenReturn(subscriber);
        when(subscriber.getSubscribedEvents()).thenReturn(subscriberContainerInterfaces);
        when(subscriber.getName()).thenReturn("test");
        when(subscriberContainerInterface.getSubscribedEvent()).thenReturn(mock(Event.class));

        final Event result = toggleActiveStateUnderTest.update(event);

        assertNull(result);
    }

    @Test
    void testGetName() {



        final String result = toggleActiveStateUnderTest.getName();


        assertEquals("cli:StoreItem", result);
    }
    @Test
    void testSetActive() {
        toggleActiveStateUnderTest.setActive(false);

        assertFalse(toggleActiveStateUnderTest.isActive());
    }
}
