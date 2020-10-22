package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.events.DeleteWarehouseEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteWarehouseTest {

    private DeleteWarehouse deleteWarehouseUnderTest;

    @BeforeEach
    void setUp() {
        deleteWarehouseUnderTest = new DeleteWarehouse();
        deleteWarehouseUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = deleteWarehouseUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), DeleteWarehouseEvent.class);


    }

    @Test
    void testUpdatePassNull() {
        final Event event = null;

        final Event result = deleteWarehouseUnderTest.update(event);

        assertNull(result);
        verify(deleteWarehouseUnderTest.warehouseManager, never()).removeWarehouse("warehouseId");
    }

    @Test
    void testUpdate() {
        final DeleteWarehouseEvent event = mock(DeleteWarehouseEvent.class);
        when(event.getId()).thenReturn("warehouseId");

        final Event result = deleteWarehouseUnderTest.update(event);

        assertNull(result);
        verify(deleteWarehouseUnderTest.warehouseManager).removeWarehouse("warehouseId");
    }

    @Test
    void testGetName() {



        final String result = deleteWarehouseUnderTest.getName();


        assertEquals("warehouse:DeleteWarehouse", result);
    }

    @Test
    void testSetActive() {
        deleteWarehouseUnderTest.setActive(false);

        assertFalse(deleteWarehouseUnderTest.isActive());
    }
}
