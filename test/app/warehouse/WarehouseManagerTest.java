package app.warehouse;

import app.warehouse.entity.Warehouse;
import famework.configReader.ConfigBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.MockitoAnnotations.initMocks;

class WarehouseManagerTest {

    @Mock
    private ConfigBag mockConfig;

    private WarehouseManager warehouseManagerUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        warehouseManagerUnderTest = new WarehouseManager(mockConfig);
    }

    @Test
    void testNewWarehouse() {
        final Warehouse warehouse = new Warehouse("id");

        warehouseManagerUnderTest.newWarehouse(warehouse);
        ArrayList<Warehouse> warehouses = warehouseManagerUnderTest.getWarehouses();
        assertEquals(warehouse, warehouses.get(0));

    }
    @Test
    void testNewWarehouseWhenExists() {
        final Warehouse warehouse = new Warehouse("id");

        warehouseManagerUnderTest.newWarehouse(warehouse);
        warehouseManagerUnderTest.newWarehouse(warehouse);
        ArrayList<Warehouse> warehouses = warehouseManagerUnderTest.getWarehouses();
        assertEquals(1, warehouses.size());

    }

    @Test
    void testRemoveWarehouse() {
        final Warehouse warehouse = new Warehouse("warehouseId");

        warehouseManagerUnderTest.newWarehouse(warehouse);

        warehouseManagerUnderTest.removeWarehouse("warehouseId");
        ArrayList<Warehouse> warehouses = warehouseManagerUnderTest.getWarehouses();

        assertEquals(0, warehouses.size());
    }

    @Test
    void testFindWarehouseByIdNull() {
        final Warehouse result = warehouseManagerUnderTest.findWarehouseById("warehouseId");

        assertNull(result);
    }
    @Test
    void testFindWarehouseById() {
        final Warehouse warehouse = new Warehouse("warehouseId");

        warehouseManagerUnderTest.newWarehouse(warehouse);

        final Warehouse result = warehouseManagerUnderTest.findWarehouseById("warehouseId");

        assertEquals(warehouse, result);
    }
}
