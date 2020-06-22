package famework.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EventHandlerTest {

    private EventHandler eventHandlerUnderTest;

    @BeforeEach
    void setUp() {
        eventHandlerUnderTest = new EventHandler();
    }

    @Test
    void testRegisterListener() {
        // Setup
        final Listener mockListener = mock(Listener.class);

        eventHandlerUnderTest.registerListener(mockListener);
        assertTrue(eventHandlerUnderTest.getRegistry().getListeners().contains(mockListener));
    }

    @Test
    void testRegisterSubscriber() {
        Subscriber subscriber = mock(Subscriber.class);
        ArrayList<SubscriberContainerInterface> subscriberContainer = new ArrayList<SubscriberContainerInterface>();
        subscriberContainer.add(new SubscriberContainer(this, 10));
        when(subscriber.getSubscribedEvents()).thenReturn(subscriberContainer);
        eventHandlerUnderTest.registerSubscriber(subscriber);
        ArrayList<Listener> listeners = eventHandlerUnderTest.getRegistry().getListeners();
        boolean found = false;
        for (Listener listener: listeners) {
            if (listener.getSubscriber().equals(subscriber)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void testPush() {
        final Event event = mock(Event.class);
        final Event result = eventHandlerUnderTest.push(event);
        assertTrue(eventHandlerUnderTest.getHistory().contains(event));

    }
}
