package app.warehouseDistributor;

import app.warehouse.entity.Warehouse;
import famework.configReader.ConfigBag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class WarehouseDistributorTest {

    @Mock
    private ConfigBag mockConfig;

    private WarehouseDistributor warehouseDistributorUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        warehouseDistributorUnderTest = new WarehouseDistributor(mockConfig);
    }

    @Test
    void testNewWarehouse() {
        final Warehouse warehouse = new Warehouse("id");

        warehouseDistributorUnderTest.newWarehouse(warehouse);

        assertEquals(warehouse, warehouseDistributorUnderTest.findWarehouseById("id"));
    }

    @Test
    void testRemoveWarehouse() {
        final Warehouse warehouse = new Warehouse("warehouseId");

        warehouseDistributorUnderTest.newWarehouse(warehouse);

        warehouseDistributorUnderTest.removeWarehouse("warehouseId");

        assertFalse(warehouseDistributorUnderTest.getWarehouses().contains(warehouse));

    }

    @Test
    void testFindWarehouseByIdNull() {
        final Warehouse result = warehouseDistributorUnderTest.findWarehouseById("warehouseId");
        assertNull(result);
    }
    @Test
    void testFindWarehouseById() {
        final Warehouse warehouse = new Warehouse("warehouseId");
        warehouseDistributorUnderTest.newWarehouse(warehouse);
        final Warehouse result = warehouseDistributorUnderTest.findWarehouseById("warehouseId");
        assertEquals(warehouse, result);
    }
}
