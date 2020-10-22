package app.warehouse.model;

import app.cli.events.GetInputEvent;
import app.user.entity.User;
import app.user.events.GetUserEvent;
import app.warehouse.events.StoreItemEvent;
import famework.event.Event;
import famework.event.EventHandler;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ParseCliInputTest {

    private ParseCliInput parseCliInputUnderTest;

    @BeforeEach
    void setUp() {
        parseCliInputUnderTest = new ParseCliInput();
        parseCliInputUnderTest.eventHandler = mock(EventHandler.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = parseCliInputUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), GetInputEvent.class);

    }

    @Test
    void testUpdateNull() {

        final Event event = null;
        when(parseCliInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);

        final Event result = parseCliInputUnderTest.update(event);
        assertNull(result);
    }
    @Test
    void testUpdateInvalid() {

        final GetInputEvent event = mock(GetInputEvent.class);
        when(event.isValidated()).thenReturn(false);
        when(parseCliInputUnderTest.eventHandler.push(any(Event.class))).thenReturn(null);

        final Event result = parseCliInputUnderTest.update(event);
        assertNull(result);
    }
    @Test
    void testUpdateValidItem() {

        final GetInputEvent event = mock(GetInputEvent.class);
        final GetUserEvent getUserEvent = mock(GetUserEvent.class);
        final ArrayList<User> users = new ArrayList<>();
        final User user = mock(User.class);
        users.add(user);
        when(event.isValidated()).thenReturn(true);
        when(event.getContent()).thenReturn("Item Beispielkunde 40.50 86400 radioactive n y y");
        when(parseCliInputUnderTest.eventHandler.push(any(GetUserEvent.class))).thenReturn(getUserEvent);
        when(getUserEvent.getUsers()).thenReturn(users);
        final Event result = parseCliInputUnderTest.update(event);
        assertNull(result);
        verify(parseCliInputUnderTest.eventHandler).push(any(StoreItemEvent.class));
    }
    @Test
    void testUpdateValidILiquidBulkCargo() {

        final GetInputEvent event = mock(GetInputEvent.class);
        final GetUserEvent getUserEvent = mock(GetUserEvent.class);
        final ArrayList<User> users = new ArrayList<>();
        final User user = mock(User.class);
        users.add(user);
        when(event.isValidated()).thenReturn(true);
        when(event.getContent()).thenReturn("LiquidBulkCargo Beispielkunde 40.50 86400 radioactive y n n");
        when(parseCliInputUnderTest.eventHandler.push(any(GetUserEvent.class))).thenReturn(getUserEvent);
        when(getUserEvent.getUsers()).thenReturn(users);
        final Event result = parseCliInputUnderTest.update(event);
        assertNull(result);
        verify(parseCliInputUnderTest.eventHandler).push(any(StoreItemEvent.class));
    }
    @Test
    void testUpdateValidIUnitisedCargo() {

        final GetInputEvent event = mock(GetInputEvent.class);
        final GetUserEvent getUserEvent = mock(GetUserEvent.class);
        final ArrayList<User> users = new ArrayList<>();
        final User user = mock(User.class);
        users.add(user);
        when(event.isValidated()).thenReturn(true);
        when(event.getContent()).thenReturn("UnitisedCargo Beispielkunde 40.50 86400 radioactive y n n");
        when(parseCliInputUnderTest.eventHandler.push(any(GetUserEvent.class))).thenReturn(getUserEvent);
        when(getUserEvent.getUsers()).thenReturn(users);
        final Event result = parseCliInputUnderTest.update(event);
        assertNull(result);
        verify(parseCliInputUnderTest.eventHandler).push(any(StoreItemEvent.class));
    }
    @Test
    void testUpdateValidMixedCargoLiquidBulkAndUnitised() {

        final GetInputEvent event = mock(GetInputEvent.class);
        final GetUserEvent getUserEvent = mock(GetUserEvent.class);
        final ArrayList<User> users = new ArrayList<>();
        final User user = mock(User.class);
        users.add(user);
        when(event.isValidated()).thenReturn(true);
        when(event.getContent()).thenReturn("MixedCargoLiquidBulkAndUnitised Beispielkunde 40.50 86400 radioactive y n n");
        when(parseCliInputUnderTest.eventHandler.push(any(GetUserEvent.class))).thenReturn(getUserEvent);
        when(getUserEvent.getUsers()).thenReturn(users);
        final Event result = parseCliInputUnderTest.update(event);
        assertNull(result);
        verify(parseCliInputUnderTest.eventHandler).push(any(StoreItemEvent.class));
    }

    @Test
    void testParseTime1() {
        final Date result = parseCliInputUnderTest.parseTime(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), 0);

        assertEquals(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), result);
    }

    @Test
    void testGetName() {
        final String result = parseCliInputUnderTest.getName();


        assertEquals("warehouse:ParseCliInput", result);
    }

    @Test
    void testSetActive() {
        parseCliInputUnderTest.setActive(false);

        assertFalse(parseCliInputUnderTest.isActive());
    }
    @Test
    void testSParseItme() {
        Date result = parseCliInputUnderTest.parseTime(new Date(), 10);

        assertTrue(result instanceof Date);
    }
}
