package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.CreateWarehouseEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateWarehouseTest {

    private CreateWarehouse createWarehouseUnderTest;

    @BeforeEach
    void setUp() {
        createWarehouseUnderTest = new CreateWarehouse();
        createWarehouseUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = createWarehouseUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), CreateWarehouseEvent.class);


    }

    @Test
    void testUpdateNull() {
        final Event event = null;
        when(createWarehouseUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(new Warehouse("id"));

        final Event result = createWarehouseUnderTest.update(event);

        assertNull(result);
        verify(createWarehouseUnderTest.warehouseManager,never()).newWarehouse(any(Warehouse.class));
    }
    @Test
    void testUpdateAlreadyExist() {
        final CreateWarehouseEvent event = mock(CreateWarehouseEvent.class);
        when(event.getId()).thenReturn("warehouseId");

        when(createWarehouseUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(new Warehouse("id"));

        final Event result = createWarehouseUnderTest.update(event);

        assertNull(result);
        verify(createWarehouseUnderTest.warehouseManager,never()).newWarehouse(any(Warehouse.class));
    }
    @Test
    void testUpdateCreateNew() {
        final CreateWarehouseEvent event = mock(CreateWarehouseEvent.class);
        when(event.getId()).thenReturn("warehouseId");
        when(createWarehouseUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(null);

        final Event result = createWarehouseUnderTest.update(event);

        assertNull(result);
        verify(createWarehouseUnderTest.warehouseManager).newWarehouse(any(Warehouse.class));
    }

    @Test
    void testGetName() {



        final String result = createWarehouseUnderTest.getName();


        assertEquals("warehouse:CreateWarehouse", result);
    }

    @Test
    void testSetActive() {
        createWarehouseUnderTest.setActive(false);

        assertFalse(createWarehouseUnderTest.isActive());
    }
}
