package app;

import app.events.Connectable;
import app.events.Module;
import app.events.ModuleEvent;
import app.events.RegisterModuleEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * We do not need to test every method in this Class
 * - if every module is working
 */
class AppTest {

    private App appUnderTest;

    @BeforeEach
    void setUp() {
        appUnderTest = new App();
    }

    @Test
    void testGetUnkownModule() {
        final Module result = appUnderTest.getModule("unkown");
        assertNull(result);
    }

    @Test
    void testGetKownModule() {
        Module module = Mockito.mock(Module.class);
        Mockito.when(module.getName()).thenReturn("module");

        RegisterModuleEvent RegisterModuleEvent = Mockito.mock(RegisterModuleEvent.class);
        Mockito.when(RegisterModuleEvent.registerModule(appUnderTest)).thenReturn(module);

        appUnderTest.addModule(RegisterModuleEvent);
        appUnderTest.registerModule();

        final Module result = appUnderTest.getModule("module");

        assertEquals(module, result);
    }
}
