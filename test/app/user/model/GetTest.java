package app.user.model;

import app.user.UserManager;
import app.user.entity.User;
import app.user.events.GetUserEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetTest {

    private Get getUnderTest;

    @BeforeEach
    void setUp() {
        getUnderTest = new Get();
        getUnderTest.userManager = mock(UserManager.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = getUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), GetUserEvent.class);

    }

    @Test
    void testUpdate() {
        final ArrayList<User> users = new ArrayList<>(Arrays.asList(new User("username")));

        final Event event = new GetUserEvent(users);

        when(getUnderTest.userManager.getUser()).thenReturn(users);

        final ArrayList<User> users1 = new ArrayList<>(Arrays.asList(new User("username")));
        when(getUnderTest.userManager.getUser("username")).thenReturn(users1);

        final GetUserEvent result = (GetUserEvent) getUnderTest.update(event);

        assertTrue(result instanceof GetUserEvent);
    }
    @Test
    void testUpdateForwardEmpty() {
        final Event event = null;

        final Event result = getUnderTest.update(event);

        assertNull(result);
    }
    @Test
    void testUpdateFiltered() {
        final ArrayList<User> users = new ArrayList<>(Arrays.asList(new User("username")));

        final GetUserEvent event = new GetUserEvent(users);
        event.setFilterByName("test");

        when(getUnderTest.userManager.getUser()).thenReturn(users);
        when(getUnderTest.userManager.getUser()).thenReturn(users);

        final ArrayList<User> users1 = new ArrayList<>(Arrays.asList(new User("username")));
        when(getUnderTest.userManager.getUser("username")).thenReturn(users1);

        final GetUserEvent result = (GetUserEvent) getUnderTest.update(event);

        assertTrue(result instanceof GetUserEvent);
    }

    @Test
    void testGetName() {
        final String result = getUnderTest.getName();

        assertEquals("user:Get", result);
    }

    @Test
    void testSetActive() {
        getUnderTest.setActive(false);

        assertFalse(getUnderTest.isActive());
    }
}
