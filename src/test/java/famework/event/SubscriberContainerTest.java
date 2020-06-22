package famework.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class SubscriberContainerTest {

    @Test
    void testGetSubscribedEvent() {
        Event event = mock(Event.class);
        SubscriberContainer subscriberContainerUnderTest = new SubscriberContainer(event, 0);

        final Event result = subscriberContainerUnderTest.getSubscribedEvent();
        assertEquals(event, result);
    }
}
