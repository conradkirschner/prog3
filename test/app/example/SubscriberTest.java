package app.example;

import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubscriberTest {

    private Subscriber subscriberUnderTest;

    @BeforeEach
    void setUp() {
        subscriberUnderTest = new Subscriber();
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = subscriberUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), TestEvent.class);
    }

    @Test
    void testUpdate() {
        final Event result = subscriberUnderTest.update(null);

        assertNull(result);
    }

    @Test
    void testGetName() {
        final String result = subscriberUnderTest.getName();

        assertEquals("example:Subscriber", result);
    }
    @Test
    void testSetActive() {
        subscriberUnderTest.setActive(false);

        assertFalse(subscriberUnderTest.isActive());
    }
}
