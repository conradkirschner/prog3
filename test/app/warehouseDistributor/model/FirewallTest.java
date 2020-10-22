package app.warehouseDistributor.model;

import app.warehouse.events.CreateWarehouseEvent;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.StoreItemEvent;
import app.warehouse.events.UpdateItemEvent;
import app.warehouseDistributor.events.ActivateWarehouseEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FirewallTest {

    private Firewall firewallUnderTest;

    @BeforeEach
    void setUp() {
        firewallUnderTest = new Firewall();
        firewallUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testGetSubscribedEvents() {

        final ArrayList<SubscriberContainerInterface> result = firewallUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), ActivateWarehouseEvent.class);
        assertEquals(result.get(1).getSubscribedEvent().getClass(), CreateWarehouseEvent.class);
        assertEquals(result.get(2).getSubscribedEvent().getClass(), StoreItemEvent.class);
        assertEquals(result.get(3).getSubscribedEvent().getClass(), GetItemEvent.class);
        assertEquals(result.get(4).getSubscribedEvent().getClass(), UpdateItemEvent.class);
    }

    @Test
    void testUpdate() {

        final Event event = null;
        when(firewallUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);


        final Event result = firewallUnderTest.update(event);


    }

    @Test
    void testGetName() {
        final String result = firewallUnderTest.getName();


        assertEquals("warehouseDistributor:Firewall", result);
    }
    @Test
    void testSetActive() {
        firewallUnderTest.setActive(false);

        assertFalse(firewallUnderTest.isActive());
    }
}
