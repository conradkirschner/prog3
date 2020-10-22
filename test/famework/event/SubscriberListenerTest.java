package famework.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SubscriberListenerTest {

    @Mock
    private Event mockEvent;
    @Mock
    private Subscriber mockSubscriber;

    private SubscriberListener subscriberListenerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        subscriberListenerUnderTest = new SubscriberListener(mockEvent, mockSubscriber, 0);
    }

    @Test
    void testUpdate() {

        final Event listenerEvent = mock(Event.class);
        when(mockEvent.getName()).thenReturn("result");
        when(mockSubscriber.update(any(Event.class))).thenReturn(null);
        when(listenerEvent.getName()).thenReturn("result");


        final Event result = subscriberListenerUnderTest.update(listenerEvent);
        assertNull(result);

    }

    @Test
    void testGetEvents() {
        assertEquals(mockEvent, subscriberListenerUnderTest.getEvent());
    }

    @Test
    void testGetPrio() {
        assertEquals(0, subscriberListenerUnderTest.getPrio());
    }
}
