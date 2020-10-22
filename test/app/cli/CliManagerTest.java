package app.cli;

import app.cli.events.GetInputEvent;
import app.cli.screens.Screen;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CliManagerTest {

    @Mock
    private ConfigBag mockConfigBag;
    @Mock
    private PrintStream mockPrintStream;
    @Mock
    private EventHandler mockEventHandler;

    private CliManager cliManagerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        cliManagerUnderTest = new CliManager(mockConfigBag, mockPrintStream, mockEventHandler);
    }

    @Test
    void testRun() {

        Screen mockScreen = mock(Screen.class);
        when(mockEventHandler.push(any(GetInputEvent.class))).thenReturn(null);
        doNothing().when(mockScreen).getContent();
        doNothing().when(mockScreen).getUsage();
        cliManagerUnderTest.setCurrentScreen(mockScreen);
        cliManagerUnderTest.setFlashMessage("mockScreen");

        cliManagerUnderTest.run();


        verify(mockPrintStream).println(anyString());
    }

    @Test
    void testSetPreviousScreen() {
        Screen mockScreen = mock(Screen.class);
        Screen mockScreen1 = mock(Screen.class);
        doNothing().when(mockScreen).getContent();
        doNothing().when(mockScreen).getUsage();
        cliManagerUnderTest.setCurrentScreen(mockScreen);
        cliManagerUnderTest.setCurrentScreen(mockScreen1);

        cliManagerUnderTest.setPreviousScreen();
        assertEquals(mockScreen, cliManagerUnderTest.getCurrentScreen());
    }
    @Test
    void testSetPreviousScreenFirst() {
        cliManagerUnderTest.setPreviousScreen();
        cliManagerUnderTest.setPreviousScreen();

        assertNull(cliManagerUnderTest.getCurrentScreen());
    }

    @Test
    void testShowError() {



        cliManagerUnderTest.showError("string");


        verify(mockPrintStream).println(anyString());
    }

    @Test
    void testShowFlashMassage() {
        cliManagerUnderTest.setFlashMessage("test");

        cliManagerUnderTest.showFlashMassage();

        verify(mockPrintStream).println(anyString());
    }
    @Test
    void testNotShowFlashMassage() {
        cliManagerUnderTest.showFlashMassage();

        verify(mockPrintStream, never()).println(anyString());
    }

    @Test
    void testStop() {
        cliManagerUnderTest.stop();

        assertFalse(cliManagerUnderTest.shouldRun());
    }

    @Test
    void testStart() {
        cliManagerUnderTest.start();

        assertTrue(cliManagerUnderTest.shouldRun());
    }

    @Test
    void testShouldRun() {
        final boolean result = cliManagerUnderTest.shouldRun();

        assertTrue(result);
    }
}
