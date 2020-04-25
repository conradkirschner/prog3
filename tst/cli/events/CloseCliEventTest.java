package cli.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CloseCliEventTest {

    private CloseCliEvent closeCliEventUnderTest;

    @BeforeEach
    void setUp() {
        closeCliEventUnderTest = new CloseCliEvent(0);
    }

    @Test
    void getCliEvent() {
        assertEquals(0, closeCliEventUnderTest.getStatus());
    }
}
