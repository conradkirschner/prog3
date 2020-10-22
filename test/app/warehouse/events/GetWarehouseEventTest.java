package app.warehouse.events;

import app.warehouse.entity.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class GetWarehouseEventTest {

    @Mock
    private Warehouse mockWarehouse;

    private GetWarehouseEvent getWarehouseEventUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        getWarehouseEventUnderTest = new GetWarehouseEvent(mockWarehouse);
    }

    @Test
    void testGetName() {



        final String result = getWarehouseEventUnderTest.getName();


        assertEquals(GetWarehouseEvent.class.getName(), result);
    }
    @Test
    void testGetWarehouse() {

        getWarehouseEventUnderTest = new GetWarehouseEvent(mockWarehouse);


        final Warehouse result = getWarehouseEventUnderTest.getWarehouse();


        assertEquals(mockWarehouse, result);
    }
    @Test
    void testGetWarehouseId() {

        getWarehouseEventUnderTest = new GetWarehouseEvent("test");


        final String result = getWarehouseEventUnderTest.getWarehouseId();


        assertEquals("test", result);
    }
    @Test
    void testGetWarehouses() {

        ArrayList<Warehouse> warehouses = new ArrayList<>();
        getWarehouseEventUnderTest = new GetWarehouseEvent(warehouses);


        final ArrayList<Warehouse> result = getWarehouseEventUnderTest.getWarehouses();


        assertEquals(warehouses, result);
    }
}
