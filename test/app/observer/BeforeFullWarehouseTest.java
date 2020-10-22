package app.observer;

import app.warehouse.entity.StoragePlace;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.GetWarehouseEvent;
import app.warehouse.events.StoreItemEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.beans.PropertyChangeEvent;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BeforeFullWarehouseTest {

    @Mock
    private PrintStream mockPrintStream;
    @Mock
    private EventHandler mockEventHandler;

    private BeforeFullWarehouse beforeFullWarehouseUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        beforeFullWarehouseUnderTest = new BeforeFullWarehouse(mockPrintStream, mockEventHandler);
    }

    @Test
    void testPropertyChange() {
        final PropertyChangeEvent evt = new PropertyChangeEvent("o", "s", "o1", "o2");

        beforeFullWarehouseUnderTest.propertyChange(evt);

        verify(mockPrintStream).println(anyString());
    }

    @Test
    void testGetSubscribedEvents() {

        final ArrayList<SubscriberContainerInterface> result = beforeFullWarehouseUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), StoreItemEvent.class);

    }

    @Test
    void testUpdate() {

        final StoreItemEvent event = mock(StoreItemEvent.class);
        ArrayList<Warehouse> warehouses = new ArrayList<>();
        ArrayList<StoragePlace> storagePlace = new ArrayList<StoragePlace>();
        Warehouse warehouseMock = mock(Warehouse.class);
        StoragePlace storagePlaceMock = mock(StoragePlace.class);
        warehouses.add(warehouseMock);
        storagePlace.add(storagePlaceMock);
        when(mockEventHandler.push(any(GetWarehouseEvent.class))).thenReturn(new GetWarehouseEvent(warehouses));
        when(warehouseMock.getStoragePlaces()).thenReturn(storagePlace);
        when(storagePlaceMock.getLeftSpace()).thenReturn(new BigDecimal(10));


        final Event result = beforeFullWarehouseUnderTest.update(event);


    }

    @Test
    void testGetName() {



        final String result = beforeFullWarehouseUnderTest.getName();


        assertEquals("observer:BeforeFullWarehouse", result);
    }
    @Test
    void testSetActive() {
        beforeFullWarehouseUnderTest.setActive(false);

        assertFalse(beforeFullWarehouseUnderTest.isActive());
    }
}
