package app.cli.model;

import app.cli.CliManager;
import app.user.entity.User;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.StoreItemEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class StoreServiceItemTest {

    private StoreItem storeItemUnderTest;

    @BeforeEach
    void setUp() {
        storeItemUnderTest = new StoreItem();
        storeItemUnderTest.cliManager = mock(CliManager.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = storeItemUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), StoreItemEvent.class);
    }

    @Test
    void testUpdateFailure() {

        final Event event = new StoreItemEvent(new Item(
                new BigDecimal(10),
                new User("test"),
                new ArrayList<>(),
                new Date(),
                "test",
                0
        ), new Warehouse("test"));


        final Event result = storeItemUnderTest.update(event);


        verify(storeItemUnderTest.cliManager, never()).setFlashMessage(anyString());
        verify(storeItemUnderTest.cliManager).showError("Konnte Item nicht speichern");
    }
    @Test
    void testUpdate() {

        final Event event = new StoreItemEvent(
                new Item(
                    new BigDecimal(10),
                    new User("test"),
                    new ArrayList<>(),
                    new Date(),
                    "test",
                    0
                ),
                new Warehouse("test"),
                true);


        final Event result = storeItemUnderTest.update(event);


        verify(storeItemUnderTest.cliManager, times(1)).setFlashMessage("Item gespeichert!");
        verify(storeItemUnderTest.cliManager, never()).showError("Konnte Item nicht speichern");
    }

    @Test
    void testGetName() {



        final String result = storeItemUnderTest.getName();


        assertEquals("cli:StoreItem", result);
    }
    @Test
    void testSetActive() {
        storeItemUnderTest.setActive(false);

        assertFalse(storeItemUnderTest.isActive());
    }
}
