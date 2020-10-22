package app.cli.helper;

import famework.event.Event;
import famework.event.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class ConnectionBridgeTest {

    @Mock
    private Event mockEvent;
    @Mock
    private Subscriber mockSubscriber;

    private ConnectionBridge connectionBridgeUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        connectionBridgeUnderTest = new ConnectionBridge(mockEvent, mockSubscriber);
    }

    @Test
    public void testGetEvent() {
        Event result = connectionBridgeUnderTest.getEvent();
        assertEquals(mockEvent, result);
    }
    @Test
    public void testGetSubscriber() {
        Subscriber result = connectionBridgeUnderTest.getSubscriber();
        assertEquals(mockSubscriber, result);
    }
}
