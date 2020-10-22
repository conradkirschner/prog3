package app.cli.model;

import app.cli.CliManager;
import app.cli.events.GetInputEvent;
import app.cli.events.ToggleActiveStateEvent;
import app.cli.screens.*;
import app.cli.validators.*;
import app.persistence.events.SaveApplicationEvent;
import app.user.entity.User;
import app.user.events.DeleteUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.events.*;
import famework.configReader.ConfigBag;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParseInputTest {

    private ParseInput parseInputUnderTest;

    @BeforeEach
    void setUp() {
        parseInputUnderTest = new ParseInput();
        parseInputUnderTest.deleteScreen = mock(DeleteScreen.class);
        parseInputUnderTest.insertScreen = mock(InsertScreen.class);
        parseInputUnderTest.mainScreen = mock(MainScreen.class);
        parseInputUnderTest.overviewScreen = mock(OverviewScreen.class);
        parseInputUnderTest.updateScreen = mock(UpdateScreen.class);
        parseInputUnderTest.configScreen = mock(ConfigScreen.class);
        parseInputUnderTest.persistenceScreen = mock(PersistenceScreen.class);
        parseInputUnderTest.newUserInput = mock(NewUserInput.class);
        parseInputUnderTest.newCargoInput = mock(NewCargoInput.class);
        parseInputUnderTest.searchInput = mock(SearchInput.class);
        parseInputUnderTest.deleteInput = mock(DeleteInput.class);
        parseInputUnderTest.updateInput = mock(UpdateInput.class);
        parseInputUnderTest.persistenceInput = mock(PersistenceInput.class);
        parseInputUnderTest.configInput = mock(ConfigInput.class);
        parseInputUnderTest.cliManager = mock(CliManager.class);
        parseInputUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = parseInputUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), GetInputEvent.class);
    }


    @Test
    void testUpdateCommandC() {

        final Event event = new GetInputEvent(":c");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);
        when(parseInputUnderTest.newUserInput.get()).thenReturn("result");
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getArg2()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getHazardFilter()).thenReturn(false);
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("result");
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("result");
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.updateInput.getId()).thenReturn("result");
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.persistenceInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.isValid("input")).thenReturn(false);
        when(parseInputUnderTest.configInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn(new String[]{"value"});
        when(parseInputUnderTest.configInput.getSize()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageNumber()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageName()).thenReturn("result");


        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.cliManager).setCurrentScreen(any(Screen.class));
        verify(parseInputUnderTest.overviewScreen, never()).setMode("mode");
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage("flashMessage");
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
        verify(parseInputUnderTest.cliManager, never()).stop();
    }
    @Test
    void testUpdateCommandD() {

        final Event event = new GetInputEvent(":d");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);
        when(parseInputUnderTest.newUserInput.get()).thenReturn("result");
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getArg2()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getHazardFilter()).thenReturn(false);
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("result");
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("result");
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.updateInput.getId()).thenReturn("result");
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.persistenceInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.isValid("input")).thenReturn(false);
        when(parseInputUnderTest.configInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn(new String[]{"value"});
        when(parseInputUnderTest.configInput.getSize()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageNumber()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageName()).thenReturn("result");


        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.cliManager).setCurrentScreen(any(Screen.class));
        verify(parseInputUnderTest.overviewScreen, never()).setMode("mode");
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage("flashMessage");
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
        verify(parseInputUnderTest.cliManager, never()).stop();
    }

    @Test
    void testUpdateCommandR() {

        final Event event = new GetInputEvent(":r");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);
        when(parseInputUnderTest.newUserInput.get()).thenReturn("result");
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getArg2()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getHazardFilter()).thenReturn(false);
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("result");
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("result");
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.updateInput.getId()).thenReturn("result");
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.persistenceInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.isValid("input")).thenReturn(false);
        when(parseInputUnderTest.configInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn(new String[]{"value"});
        when(parseInputUnderTest.configInput.getSize()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageNumber()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageName()).thenReturn("result");


        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.cliManager).setCurrentScreen(any(Screen.class));
        verify(parseInputUnderTest.overviewScreen, never()).setMode("mode");
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage("flashMessage");
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
        verify(parseInputUnderTest.cliManager, never()).stop();
    }
    @Test
    void testUpdateCommandU() {

        final Event event = new GetInputEvent(":u");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);
        when(parseInputUnderTest.newUserInput.get()).thenReturn("result");
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getArg2()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getHazardFilter()).thenReturn(false);
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("result");
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("result");
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.updateInput.getId()).thenReturn("result");
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.persistenceInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.isValid("input")).thenReturn(false);
        when(parseInputUnderTest.configInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn(new String[]{"value"});
        when(parseInputUnderTest.configInput.getSize()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageNumber()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageName()).thenReturn("result");


        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.cliManager).setCurrentScreen(any(Screen.class));
        verify(parseInputUnderTest.overviewScreen, never()).setMode("mode");
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage("flashMessage");
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
        verify(parseInputUnderTest.cliManager, never()).stop();
    }
    @Test
    void testUpdateCommandConfig() {

        final Event event = new GetInputEvent(":config");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);
        when(parseInputUnderTest.newUserInput.get()).thenReturn("result");
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getArg2()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getHazardFilter()).thenReturn(false);
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("result");
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("result");
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.updateInput.getId()).thenReturn("result");
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.persistenceInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.isValid("input")).thenReturn(false);
        when(parseInputUnderTest.configInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn(new String[]{"value"});
        when(parseInputUnderTest.configInput.getSize()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageNumber()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageName()).thenReturn("result");


        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.cliManager).setCurrentScreen(any(Screen.class));
        verify(parseInputUnderTest.overviewScreen, never()).setMode("mode");
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage("flashMessage");
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
        verify(parseInputUnderTest.cliManager, never()).stop();
    }
    @Test
    void testUpdateCommandP() {

        final Event event = new GetInputEvent(":p");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);
        when(parseInputUnderTest.newUserInput.get()).thenReturn("result");
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getArg2()).thenReturn("result");
        when(parseInputUnderTest.searchInput.getHazardFilter()).thenReturn(false);
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("result");
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("result");
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.updateInput.getId()).thenReturn("result");
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.persistenceInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.isValid("input")).thenReturn(false);
        when(parseInputUnderTest.configInput.getType()).thenReturn("result");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn(new String[]{"value"});
        when(parseInputUnderTest.configInput.getSize()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageNumber()).thenReturn(0);
        when(parseInputUnderTest.configInput.getStorageName()).thenReturn("result");


        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.cliManager).setCurrentScreen(any(Screen.class));
        verify(parseInputUnderTest.overviewScreen, never()).setMode("mode");
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage("flashMessage");
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
        verify(parseInputUnderTest.cliManager, never()).stop();
    }

    @Test
    void testUpdateCommandNone() {

        final Event event = new GetInputEvent("");
        doNothing().when(parseInputUnderTest.cliManager).stop();
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new MainScreen(System.out,new ConfigBag(new Properties())));
        final Event result = parseInputUnderTest.update(event);

    }
    @Test
    void testUpdateInsertScreenUser() {

        final Event event = new GetInputEvent("Hans");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new InsertScreen(System.out));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.newUserInput).isValid(any());
        verify(parseInputUnderTest.eventHandler).push(any(Event.class));
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateInsertScreenItem() {

        final Event event = new GetInputEvent("MixedCargoLiquidBulkAndUnitised Beispielkunde 40.50 86400 radioactive n y y");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new InsertScreen(System.out));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.newUserInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, never()).push(any(Event.class));
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateInsertScreenNone() {

        final Event event = new GetInputEvent("MixedCargoLiquidBulkAndUnitised Beispielkunde 40.50 86400 radioactive n y y");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new InsertScreen(System.out));
        when(parseInputUnderTest.newUserInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.newCargoInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.newUserInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, never()).push(any(Event.class));
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateOverviewScreenCustomer() {

        final Event event = new GetInputEvent("MixedCargoLiquidBulkAndUnitised Beispielkunde 40.50 86400 radioactive n y y");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new OverviewScreen(System.out));
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("customer");
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("test"));
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(new GetUserEvent(users));



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.searchInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, times(1)).push(any(Event.class));
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateOverviewScreenCargo() {

        final Event event = new GetInputEvent("cargo");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new OverviewScreen(System.out));
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("cargo");
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), new User("test"), new ArrayList<>(), new Date(), "test", 0));
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(new GetItemEvent(items));



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.searchInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, times(1)).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateOverviewScreenHazard() {

        final Event event = new GetInputEvent("hazard y");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new OverviewScreen(System.out));
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("hazard");
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), new User("test"), new ArrayList<>(), new Date(), "test", 0));
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(new GetItemEvent(items));



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.searchInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, times(1)).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateOverviewScreenNone() {

        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new OverviewScreen(System.out));
        when(parseInputUnderTest.searchInput.isValid(any(String[].class))).thenReturn(false);
        when(parseInputUnderTest.searchInput.getType()).thenReturn("hazard");
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(new BigDecimal(10), new User("test"), new ArrayList<>(), new Date(), "test", 0));
        when(parseInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(new GetItemEvent(items));



        final Event result = parseInputUnderTest.update(event);


        verify(parseInputUnderTest.searchInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, never()).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateDeleteScreenNone() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new DeleteScreen(System.out));
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(false);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.deleteInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, never()).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateDeleteScreenUser() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new DeleteScreen(System.out));
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn("Hans");
        when(parseInputUnderTest.eventHandler.push(any(DeleteUserEvent.class))).thenReturn(new DeleteUserEvent(true));

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.deleteInput).isValid(any());
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("User \"Hans\" erfolgreich gelöscht");
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }

    @Test
    void testUpdateDeleteScreenItem() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new DeleteScreen(System.out));
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn(null);
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("#123");
        when(parseInputUnderTest.eventHandler.push(any(DeleteItemEvent.class))).thenReturn(new DeleteItemEvent(true));

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.deleteInput).isValid(any());
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Item \"#123\" erfolgreich gelöscht");
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateDeleteScreenItemFailure() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new DeleteScreen(System.out));
        when(parseInputUnderTest.deleteInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.deleteInput.getUsername()).thenReturn(null);
        when(parseInputUnderTest.deleteInput.getPosition()).thenReturn("#123");
        when(parseInputUnderTest.eventHandler.push(any(DeleteItemEvent.class))).thenReturn(new DeleteItemEvent(false));

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.deleteInput).isValid(any());
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage(anyString());
        verify(parseInputUnderTest.cliManager, never()).setPreviousScreen();
    }
    @Test
    void testUpdateUpdateScreenNone() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new UpdateScreen(System.out));
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(false);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.updateInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, never()).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Going back");
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateUpdateScreenItem() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new UpdateScreen(System.out));
        when(parseInputUnderTest.updateInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(UpdateItemEvent.class))).thenReturn(null);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.updateInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, times(1)).push(any(UpdateItemEvent.class));
        verify(parseInputUnderTest.cliManager, never()).setFlashMessage(anyString());
        verify(parseInputUnderTest.cliManager,never()).setPreviousScreen();
    }
    @Test
    void testUpdatePersistenceScreenNone() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new PersistenceScreen(System.out));
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(false);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.persistenceInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, never()).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Going back");
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }
    @Test
    void testUpdatePersistenceScreenSaveSuccess() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new PersistenceScreen(System.out));
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(SaveApplicationEvent.class))).thenReturn(new SaveApplicationEvent(true));

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.persistenceInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, times(1)).push(any(SaveApplicationEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Erfolgreich gespeichert");
        verify(parseInputUnderTest.cliManager,times(1)).setPreviousScreen();
    }
    @Test
    void testUpdatePersistenceScreenSaveFailure() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new PersistenceScreen(System.out));
        when(parseInputUnderTest.persistenceInput.isValid(any(String[].class))).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(SaveApplicationEvent.class))).thenReturn(new SaveApplicationEvent(false));

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.persistenceInput).isValid(any());
        verify(parseInputUnderTest.eventHandler, times(1)).push(any(SaveApplicationEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Speichern gescheitert!");
        verify(parseInputUnderTest.cliManager,never()).setPreviousScreen();
    }
    @Test
    void testUpdateConfigScreenNone() {
        final Event event = new GetInputEvent("");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new ConfigScreen(System.out));
        when(parseInputUnderTest.configInput.isValid(any(String[].class))).thenReturn(false);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.eventHandler, never()).push(any(GetItemEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Going back");
        verify(parseInputUnderTest.cliManager, times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateConfigScreenStorageSuccess() {
        final Event event = new GetInputEvent("storage 1 2 3 4");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new ConfigScreen(System.out));
        when(parseInputUnderTest.configInput.isValid(any(String.class))).thenReturn(true);
        when(parseInputUnderTest.configInput.getType()).thenReturn("storage");
        when(parseInputUnderTest.configInput.getStorageNames()).thenReturn("1 2 3 4".split(" "));
        when(parseInputUnderTest.eventHandler.push(any(CreateWarehouseEvent.class))).thenReturn(null);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.configInput).isValid(anyString());
        verify(parseInputUnderTest.eventHandler, times(4)).push(any(CreateWarehouseEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Storage befehl ausgeführt: erstellt und einsatzbereit (aktiviert)");
        verify(parseInputUnderTest.cliManager,times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateConfigScreenDeactivate() {
        final Event event = new GetInputEvent("deactivate asd:Asd");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new ConfigScreen(System.out));
        when(parseInputUnderTest.configInput.getType()).thenReturn("deactivate");
        when(parseInputUnderTest.configInput.isValid(anyString())).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(ToggleActiveStateEvent.class))).thenReturn(null);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.eventHandler, times(1)).push(any(ToggleActiveStateEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Event deaktiviert");
        verify(parseInputUnderTest.cliManager,times(1)).setPreviousScreen();
    }
    @Test
    void testUpdateConfigScreenActivate() {
        final Event event = new GetInputEvent("deactivate asd:Asd");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new ConfigScreen(System.out));
        when(parseInputUnderTest.configInput.getType()).thenReturn("activate");
        when(parseInputUnderTest.configInput.isValid(anyString())).thenReturn(true);
        when(parseInputUnderTest.eventHandler.push(any(ToggleActiveStateEvent.class))).thenReturn(null);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.eventHandler, times(1)).push(any(ToggleActiveStateEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Event Aktiviert");
        verify(parseInputUnderTest.cliManager,times(1)).setPreviousScreen();
    }

    @Test
    void testUpdateConfigScreenPlace() {
        final Event event = new GetInputEvent("place dsf sdf s sf");
        when(parseInputUnderTest.cliManager.getCurrentScreen()).thenReturn(new ConfigScreen(System.out));
        when(parseInputUnderTest.configInput.getType()).thenReturn("place");
        when(parseInputUnderTest.eventHandler.push(any(CreateStoragePlaceEvent.class))).thenReturn(null);
        when(parseInputUnderTest.configInput.isValid(anyString())).thenReturn(true);

        final Event result = parseInputUnderTest.update(event);

        verify(parseInputUnderTest.eventHandler, times(1)).push(any(CreateStoragePlaceEvent.class));
        verify(parseInputUnderTest.cliManager, times(1)).setFlashMessage("Erfolgreich Storage Platz angelegt");
        verify(parseInputUnderTest.cliManager,times(1)).setPreviousScreen();
    }


    @Test
    void testGetName() {



        final String result = parseInputUnderTest.getName();


        assertEquals("cli:ParseInput", result);
    }
    @Test
    void testSetActive() {
        parseInputUnderTest.setActive(false);

        assertFalse(parseInputUnderTest.isActive());
    }
}
