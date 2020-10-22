package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.GetWarehouseEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetWarehouseTest {

    private GetWarehouse getWarehouseUnderTest;

    @BeforeEach
    void setUp() {
        getWarehouseUnderTest = new GetWarehouse();
        getWarehouseUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = getWarehouseUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), GetWarehouseEvent.class);


    }

    @Test
    void testUpdate() {

        final Event event = null;
        when(getWarehouseUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(new Warehouse("id"));


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(getWarehouseUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = getWarehouseUnderTest.update(event);


    }

    @Test
    void testGetName() {



        final String result = getWarehouseUnderTest.getName();


        assertEquals("warehouse:GetWarehouse", result);
    }

    @Test
    void testSetActive() {
        getWarehouseUnderTest.setActive(false);

        assertFalse(getWarehouseUnderTest.isActive());
    }
}
