package app.gui.model;

import app.gui.Window;
import app.gui.controller.TableUserController;
import app.gui.controller.TableWarenController;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import app.user.events.DeleteUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.events.DeleteItemEvent;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.StoreItemEvent;
import app.warehouse.events.UpdateItemEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class DataRefreshTest {

    @Mock
    private BufferedInputStream mockIn;
    @Mock
    private PrintStream mockPrintStream;

    @Mock
    private Window window;

    private DataRefresh dataRefreshUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        dataRefreshUnderTest = new DataRefresh(mockIn, mockPrintStream, window);
    }

    @Test
    void testGetSubscribedEvents() {



        final ArrayList<SubscriberContainerInterface> result = dataRefreshUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), CreateUserEvent.class);
        assertEquals(result.get(1).getSubscribedEvent().getClass(), DeleteUserEvent.class);
        assertEquals(result.get(2).getSubscribedEvent().getClass(), StoreItemEvent.class);
        assertEquals(result.get(3).getSubscribedEvent().getClass(), DeleteItemEvent.class);
        assertEquals(result.get(4).getSubscribedEvent().getClass(), UpdateItemEvent.class);


    }

    @Test
    void testUpdate() {

        final Event event = null;
        window.tableUserController = mock(TableUserController.class);
        dataRefreshUnderTest.eventHandler = mock(EventHandler.class);
        window.tableWarenController = mock(TableWarenController.class);
        doNothing().when(window.tableUserController).removeUser();
        doNothing().when(window.tableUserController).addUser(anyString());
        doNothing().when(window.tableWarenController).addItem(any(Item.class));
        ArrayList<User> users = new ArrayList<>();
        users.add(mock(User.class));
        when(dataRefreshUnderTest.eventHandler.push(any(GetUserEvent.class))).thenReturn(new GetUserEvent(users));

        ArrayList<Item> items = new ArrayList<>();
        items.add(mock(Item.class));
        when(dataRefreshUnderTest.eventHandler.push(any(GetItemEvent.class))).thenReturn(new GetItemEvent(items));


        final Event result = dataRefreshUnderTest.update(event);
        verify(window.tableUserController).removeUser();
        verify(window.tableWarenController).addItem(any(Item.class));


    }

    @Test
    void testUpdateEmptyItems() {

        final Event event = null;
        window.tableUserController = mock(TableUserController.class);
        dataRefreshUnderTest.eventHandler = mock(EventHandler.class);
        window.tableWarenController = mock(TableWarenController.class);
        doNothing().when(window.tableUserController).removeUser();
        doNothing().when(window.tableUserController).addUser(anyString());
        doNothing().when(window.tableWarenController).addItem(any(Item.class));
        ArrayList<User> users = new ArrayList<>();
        users.add(mock(User.class));
        when(dataRefreshUnderTest.eventHandler.push(any(GetUserEvent.class))).thenReturn(new GetUserEvent(users));
        when(dataRefreshUnderTest.eventHandler.push(any(GetItemEvent.class))).thenReturn(new GetItemEvent());


        final Event result = dataRefreshUnderTest.update(event);
        verify(window.tableUserController).removeUser();
        verify(window.tableWarenController, never()).addItem(any(Item.class));


    }

    @Test
    void testGetName() {



        final String result = dataRefreshUnderTest.getName();


        assertEquals("gui:DataRefresh", result);
    }

    @Test
    void testSetActive() {
        dataRefreshUnderTest.setActive(false);

        assertFalse(dataRefreshUnderTest.isActive());
    }
}
