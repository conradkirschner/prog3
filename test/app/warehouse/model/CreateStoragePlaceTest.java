package app.warehouse.model;

import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.CreateStoragePlaceEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateStoragePlaceTest {

    private CreateStoragePlace createStoragePlaceUnderTest;

    @BeforeEach
    void setUp() {
        createStoragePlaceUnderTest = new CreateStoragePlace();
        createStoragePlaceUnderTest.warehouseManager = mock(WarehouseManager.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = createStoragePlaceUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), CreateStoragePlaceEvent.class);
    }

    @Test
    void testUpdate() {
        final CreateStoragePlaceEvent event = mock(CreateStoragePlaceEvent.class);
        when(event.getWarehouseId()).thenReturn("warehouseId");
        when(event.getSpace()).thenReturn(10);
        when(event.getStoragePlaceNumber()).thenReturn(0);
        Warehouse mockedWarehouse = mock(Warehouse.class);
        when(createStoragePlaceUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(new Warehouse("test"));

        final Event result = createStoragePlaceUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateNoWarehouse() {
        final CreateStoragePlaceEvent event = mock(CreateStoragePlaceEvent.class);
        when(event.getWarehouseId()).thenReturn("warehouseId");
        when(event.getSpace()).thenReturn(10);
        when(event.getStoragePlaceNumber()).thenReturn(0);
        Warehouse mockedWarehouse = mock(Warehouse.class);
        when(createStoragePlaceUnderTest.warehouseManager.findWarehouseById("warehouseId")).thenReturn(null);

        final Event result = createStoragePlaceUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateNoWarehouseNoEvent() {
        final CreateStoragePlaceEvent event = null;

        final Event result = createStoragePlaceUnderTest.update(event);
        assertNull(result);
    }

    @Test
    void testGetName() {
        final String result = createStoragePlaceUnderTest.getName();

        assertEquals("warehouse:CreateStoragePlace", result);
    }

    @Test
    void testSetActive() {
        createStoragePlaceUnderTest.setActive(false);

        assertFalse(createStoragePlaceUnderTest.isActive());
    }
}
