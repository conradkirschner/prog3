package app.warehouse.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateStoragePlaceEventTest {

    private CreateStoragePlaceEvent createStoragePlaceEventUnderTest;

    @BeforeEach
    void setUp() {
        createStoragePlaceEventUnderTest = new CreateStoragePlaceEvent(0, 0, "warehouseId");
    }

    @Test
    void testGetName() {

        final String result = createStoragePlaceEventUnderTest.getName();

        assertEquals(CreateWarehouseEvent.class.getName(), result);
    }

    @Test
    void testGetWarehouseId() {

        final String result = createStoragePlaceEventUnderTest.getWarehouseId();

        assertEquals("warehouseId", result);
    }
    @Test
    void testGetSpace() {

        final int result = createStoragePlaceEventUnderTest.getSpace();

        assertEquals(0, result);
    }
    @Test
    void testGetStoragePlaceNumber() {

        final int result = createStoragePlaceEventUnderTest.getStoragePlaceNumber();

        assertEquals(0, result);
    }

}
