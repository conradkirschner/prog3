package app.warehouse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WarehouseListTest {

    private WarehouseList warehouseListUnderTest;

    @BeforeEach
    void setUp() {
        warehouseListUnderTest = new WarehouseList();
    }

    @Test
    void testToString() {
        final String result = warehouseListUnderTest.toString();

        assertEquals("null", result);
    }
    @Test
    void testGetWarehouseList() {
        List<Warehouse> testList = new ArrayList<>();

        warehouseListUnderTest.setWarehouseList(testList);
        final List<Warehouse> result = warehouseListUnderTest.getWarehouseList();

        assertEquals(testList, result);
    }
    @Test
    void testToStringWithValues() {
        Warehouse mockedWarehouse = mock(Warehouse.class);
        when(mockedWarehouse.toString()).thenReturn("works");
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(mockedWarehouse);
        warehouseListUnderTest.setWarehouseList(warehouses);
        final String result = warehouseListUnderTest.toString();

        assertEquals("[works]", result);
    }
}
