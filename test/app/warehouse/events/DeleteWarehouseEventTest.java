package app.warehouse.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteWarehouseEventTest {

    private DeleteWarehouseEvent deleteWarehouseEventUnderTest;

    @BeforeEach
    void setUp() {
        deleteWarehouseEventUnderTest = new DeleteWarehouseEvent();
    }

    @Test
    void testGetName() {
        final String result = deleteWarehouseEventUnderTest.getName();

        assertEquals(DeleteWarehouseEvent.class.getName(), result);
    }
    @Test
    void testGetId() {
        deleteWarehouseEventUnderTest = new DeleteWarehouseEvent("test");
        final String result = deleteWarehouseEventUnderTest.getId();

        assertEquals("test", result);
    }
}
