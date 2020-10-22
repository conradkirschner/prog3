package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Type;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.GetItemEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetItemTest {

    private GetItem getItemUnderTest;

    @BeforeEach
    void setUp() {
        getItemUnderTest = new GetItem();
        getItemUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = getItemUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), GetItemEvent.class);


    }

    @Test
    void testUpdateNull() {

        final Event event = null;


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(getItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = getItemUnderTest.update(event);

        assertEquals(event, result);

    }

    @Test
    void testUpdateId() {

        final Event event = new GetItemEvent("test");


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(getItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = getItemUnderTest.update(event);

        assertTrue(result instanceof GetItemEvent);

    }
    @Test
    void testUpdateType() {

        final Event event = new GetItemEvent(new Type("test"));


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(getItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = getItemUnderTest.update(event);

        assertTrue(result instanceof GetItemEvent);

    }
    @Test
    void testUpdateHazardFilter() {

        final Event event = new GetItemEvent(true);


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(getItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = getItemUnderTest.update(event);

        assertTrue(result instanceof GetItemEvent);

    }
    @Test
    void testUpdateHazardFilterFalse() {

        final Event event = new GetItemEvent(false);


        final ArrayList<Warehouse> warehouses = new ArrayList<>(Arrays.asList(new Warehouse("id")));
        when(getItemUnderTest.warehouseManager.getWarehouses()).thenReturn(warehouses);


        final Event result = getItemUnderTest.update(event);

        assertTrue(result instanceof GetItemEvent);

    }

    @Test
    void testGetName() {



        final String result = getItemUnderTest.getName();


        assertEquals("warehouse:GetItem", result);
    }

    @Test
    void testSetActive() {
        getItemUnderTest.setActive(false);

        assertFalse(getItemUnderTest.isActive());
    }
}
