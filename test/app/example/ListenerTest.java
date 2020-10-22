package app.example;

import famework.event.Event;
import famework.event.Subscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

class ListenerTest {

    @Mock
    private PrintStream mockPrintStream;

    private Listener listenerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        listenerUnderTest = new Listener(mockPrintStream);
    }

    @Test
    void testUpdate() {
        final Event event = null;

        final Event result = listenerUnderTest.update(event);

        assertNull(result);
    }

    @Test
    void testGetPrio() {
        final int result = listenerUnderTest.getPrio();

        assertEquals(0, result);
    }

    @Test
    void testGetSubscriber() {

        final Subscriber result = listenerUnderTest.getSubscriber();

        assertNull(result);
    }
}
