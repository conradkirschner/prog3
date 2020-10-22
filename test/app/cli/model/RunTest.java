package app.cli.model;

import app.cli.CliManager;
import app.cli.events.CliStartEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @Todo Doesn't include the infinity Loop Protection
 */
class RunTest {

    @Mock
    private BufferedInputStream mockIn;
    @Mock
    private PrintStream mockPrintStream;
    @Mock
    private CliManager cliManager;

    private Run runUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        runUnderTest = new Run(mockIn, mockPrintStream);
        runUnderTest.cliManager = this.cliManager;
    }

    @Test
    void testGetSubscribedEvents() {

        final ArrayList<SubscriberContainerInterface> result = runUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), CliStartEvent.class);

    }

    @Test
    void testUpdate() {

        final Event event = null;



        final Event result = runUnderTest.update(event);


        verify(mockPrintStream).println("Do you wanna exit, press y");
        verify(runUnderTest.cliManager).start();
    }

    @Test
    void testGetName() {



        final String result = runUnderTest.getName();


        assertEquals("cli:Run", result);
    }
    @Test
    void testSetActive() {
        runUnderTest.setActive(false);

        assertFalse(runUnderTest.isActive());
    }
}
