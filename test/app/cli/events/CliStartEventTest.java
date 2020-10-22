package app.cli.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CliStartEventTest {

    private CliStartEvent cliStartEventUnderTest;

    @BeforeEach
    void setUp() {
        cliStartEventUnderTest = new CliStartEvent();
    }

    @Test
    void testGetName() {
        final String result = cliStartEventUnderTest.getName();

        assertEquals(CliStartEvent.class.getName(), result);
    }
}
