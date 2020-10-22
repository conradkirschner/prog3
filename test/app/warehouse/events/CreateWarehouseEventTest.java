package app.warehouse.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateWarehouseEventTest {

    private CreateWarehouseEvent createWarehouseEventUnderTest;

    @BeforeEach
    void setUp() {
        createWarehouseEventUnderTest = new CreateWarehouseEvent("id");
    }

    @Test
    void testGetName() {
        final String result = createWarehouseEventUnderTest.getName();

        assertEquals(CreateWarehouseEvent.class.getName(), result);
    }
    @Test
    void testGetId() {
        createWarehouseEventUnderTest.setId("test");
        final String result = createWarehouseEventUnderTest.getId();

        assertEquals("test", result);
    }
}
