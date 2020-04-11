package app;

import app.events.Module;
import app.events.RegisterModuleEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

    private App appUnderTest;

    @BeforeEach
    void setUp() {
        appUnderTest = new App();
    }

    @Test
    void testGetModule() {
        // Setup

        // Run the test
        final Module result = appUnderTest.getModule("name");

        // Verify the results
    }

    @Test
    void testAddModule() {
        // Setup
        final RegisterModuleEvent toAdd = null;

        // Run the test
        appUnderTest.addModule(toAdd);

        // Verify the results
    }

    @Test
    void testRegisterModule() {
        // Setup

        // Run the test
        appUnderTest.registerModule();

        // Verify the results
    }
}
