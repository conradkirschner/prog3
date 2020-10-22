package app.gui.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarehouseTest {

    private Warehouse warehouseUnderTest;

    @BeforeEach
    void setUp() {
        warehouseUnderTest = new Warehouse("name");
    }

    @Test
    void testGetName() {
        assertEquals("name", warehouseUnderTest.getName());
    }
}
