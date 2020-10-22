package app.cli.model;

import app.cli.events.GetInputEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

class GetInputTest {

    @Mock
    private BufferedInputStream mockIn;

    private GetInput getInputUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        InputStream mockIn = new ByteArrayInputStream("test data".getBytes());

        getInputUnderTest = new GetInput(new BufferedInputStream(mockIn));
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = getInputUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), GetInputEvent.class);
    }

    @Test
    void testUpdate() {
        final Event event = null;

        final GetInputEvent result = (GetInputEvent) getInputUnderTest.update(event);
        assertEquals("test data", result.getContent());

    }
    @Test
    void testUpdateException() {
        final Event event = null;
        getInputUnderTest = new GetInput(null);

        final GetInputEvent result = (GetInputEvent) getInputUnderTest.update(event);
        assertEquals(null, result.getContent());

    }

    @Test
    void testGetName() {
        final String result = getInputUnderTest.getName();

        assertEquals("cli:GetInput", result);
    }
    @Test
    void testSetActive() {
        getInputUnderTest.setActive(false);

        assertFalse(getInputUnderTest.isActive());
    }
}
