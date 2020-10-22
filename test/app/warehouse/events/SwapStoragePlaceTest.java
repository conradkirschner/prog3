package app.warehouse.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwapStoragePlaceTest {

    private SwapStoragePlace swapStoragePlaceUnderTest;

    @BeforeEach
    void setUp() {
        swapStoragePlaceUnderTest = new SwapStoragePlace();
    }

    @Test
    void testGetName() {
        final String result = swapStoragePlaceUnderTest.getName();

        assertEquals(SwapStoragePlace.class.getName(), result);
    }
    @Test
    void testGetItemA() {
        swapStoragePlaceUnderTest = new SwapStoragePlace("itemIdA", "itemIdB", "warehouseId");

        final String result = swapStoragePlaceUnderTest.getItemIdA();

        assertEquals("itemIdA", result);
    }
    @Test
    void testGetItemB() {
        swapStoragePlaceUnderTest = new SwapStoragePlace("itemIdA", "itemIdB", "warehouseId");

        final String result = swapStoragePlaceUnderTest.getItemIdB();

        assertEquals("itemIdB", result);
    }
    @Test
    void testGetItemWareHouseId() {
        swapStoragePlaceUnderTest = new SwapStoragePlace("itemIdA", "itemIdB", "warehouseId");

        final String result = swapStoragePlaceUnderTest.getWarehouseId();

        assertEquals("warehouseId", result);
    }
}
