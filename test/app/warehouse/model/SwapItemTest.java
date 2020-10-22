package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.SwapStoragePlace;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SwapItemTest {

    private SwapItem swapItemUnderTest;

    @BeforeEach
    void setUp() {
        swapItemUnderTest = new SwapItem();
        swapItemUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = swapItemUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), SwapStoragePlace.class);


    }

    @Test
    void testUpdate() {

        final Event event = null;
        when(swapItemUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(new Warehouse("id"));


        final Event result = swapItemUnderTest.update(event);


    }

    @Test
    void testGetName() {



        final String result = swapItemUnderTest.getName();


        assertEquals("warehouse:SwapItem", result);
    }

    @Test
    void testSetActive() {
        swapItemUnderTest.setActive(false);

        assertFalse(swapItemUnderTest.isActive());
    }
}
