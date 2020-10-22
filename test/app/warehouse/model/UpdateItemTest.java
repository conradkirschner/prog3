package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.UpdateItemEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateItemTest {

    private UpdateItem updateItemUnderTest;

    @BeforeEach
    void setUp() {
        updateItemUnderTest = new UpdateItem();
        updateItemUnderTest.warehouseManager = mock(WarehouseManager.class);
        updateItemUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = updateItemUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), UpdateItemEvent.class);


    }

    @Test
    void testUpdate() {

        final Event event = null;
        when(updateItemUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(updateItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = updateItemUnderTest.update(event);


    }

    @Test
    void testGetName() {



        final String result = updateItemUnderTest.getName();


        assertEquals("warehouse:UpdateItem", result);
    }

    @Test
    void testSetActive() {
        updateItemUnderTest.setActive(false);

        assertFalse(updateItemUnderTest.isActive());
    }
}
