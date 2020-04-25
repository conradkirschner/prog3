package cli.events;

import app.App;
import app.events.Event;
import app.events.Module;
import cli.Cli;
import cli.helper.CliInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ModuleEventTest {

    private ModuleEvent moduleEventUnderTest;

    @BeforeEach
    void setUp() {
        moduleEventUnderTest = new ModuleEvent("command", "data");
    }

    @Test
    void testStopRun() {
        moduleEventUnderTest.stopRun();
        assertFalse(moduleEventUnderTest.shouldRun());
    }

    @Test
    void testReturnHereFlip() {
        moduleEventUnderTest.returnHere();
        assertTrue(moduleEventUnderTest.shouldReturn());
        assertFalse(moduleEventUnderTest.shouldReturn());
    }

    @Test
    void testReturnStop() {

        moduleEventUnderTest.returnStop();
        assertFalse(moduleEventUnderTest.shouldReturn);
    }

    @Test
    void testRunModuleEventWithRegisteredCommand() throws Exception {
        Cli cli = Mockito.mock(Cli.class);
        cli.Module cliModule = Mockito.mock( cli.Module.class);
        App app = Mockito.mock(App.class);
        Mockito.when(app.getModule("cli")).thenReturn(cliModule);
        Mockito.when(app.getModule("cli-input")).thenReturn(new CliInput(new BufferedReader(new InputStreamReader(System.in))));
        Mockito.when(app.getModule("cli-output")).thenReturn(cliModule);
        Mockito.when(cliModule.getModule()).thenReturn(cli);
        Mockito.when(cliModule.getModule().start()).thenReturn(null);

        final Event event = null;

        final Event result = moduleEventUnderTest.runModuleEvent("cli:start", "", app, event);

        Mockito.verify(cliModule, Mockito.times(2)).getModule();
    }
    @Test
    void testRunModuleEventWithWrongCommand() throws Exception {
        Cli cli = Mockito.mock(Cli.class);
        cli.Module cliModule = Mockito.mock( cli.Module.class);
        App app = Mockito.mock(App.class);
        Mockito.when(app.getModule("cli")).thenReturn(cliModule);
        Mockito.when(app.getModule("cli-input")).thenReturn(new CliInput(new BufferedReader(new InputStreamReader(System.in))));
        Mockito.when(app.getModule("cli-output")).thenReturn(cliModule);
        Mockito.when(cliModule.getModule()).thenReturn(cli);
        Mockito.when(cliModule.getModule().start()).thenReturn(null);

        final Event event = null;

        final Event result = moduleEventUnderTest.runModuleEvent("cdfagdfgadgdfgdfgart", "", app, event);

        Mockito.verify(cliModule, Mockito.times(1)).getModule();
    }

    @Test
    void testRunModuleEvent_ThrowsIOException() {
        final App app = new App();
        final Event event = null;
        assertThrows(IOException.class, () -> {
            moduleEventUnderTest.runModuleEvent("command", "data", app, event);
        });
    }

    @Test
    void testRunModuleEvent_ThrowsParseException() {
        final App app = new App();
        final Event event = null;
        assertThrows(ParseException.class, () -> {
            moduleEventUnderTest.runModuleEvent("command", "data", app, event);
        });
    }

}
