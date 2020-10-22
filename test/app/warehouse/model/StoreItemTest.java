package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.StoreItemEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StoreItemTest {

    private StoreItem storeItemUnderTest;

    @BeforeEach
    void setUp() {
        storeItemUnderTest = new StoreItem();
        storeItemUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = storeItemUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), StoreItemEvent.class);


    }

    @Test
    void testUpdate() {

        final Event event = null;
        when(storeItemUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(new Warehouse("id"));


        final Event result = storeItemUnderTest.update(event);


    }

    @Test
    void testGetName() {



        final String result = storeItemUnderTest.getName();


        assertEquals("warehouse:StoreItem", result);
    }

    @Test
    void testSetActive() {
        storeItemUnderTest.setActive(false);

        assertFalse(storeItemUnderTest.isActive());
    }
}
