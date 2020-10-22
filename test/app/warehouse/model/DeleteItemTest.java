package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.DeleteItemEvent;
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

class DeleteItemTest {

    private DeleteItem deleteItemUnderTest;

    @BeforeEach
    void setUp() {
        deleteItemUnderTest = new DeleteItem();
        deleteItemUnderTest.warehouseManager = mock(WarehouseManager.class);
        deleteItemUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = deleteItemUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), DeleteItemEvent.class);


    }

    @Test
    void testUpdate() {

        final Event event = null;


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(deleteItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);

        when(deleteItemUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);


        final Event result = deleteItemUnderTest.update(event);


    }

    @Test
    void testGetName() {



        final String result = deleteItemUnderTest.getName();


        assertEquals("warehouse:DeleteItem", result);
    }

    @Test
    void testSetActive() {
        deleteItemUnderTest.setActive(false);

        assertFalse(deleteItemUnderTest.isActive());
    }
}
