package app.user.model;

import app.user.UserManager;
import app.user.events.DeleteUserEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteTest {

    private Delete deleteUnderTest;

    @BeforeEach
    void setUp() {
        deleteUnderTest = new Delete();
        deleteUnderTest.userManager = mock(UserManager.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = deleteUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), DeleteUserEvent.class);
    }

    @Test
    void testUpdate() {
        final Event event = new DeleteUserEvent("username");
        when(deleteUnderTest.userManager.removeUser("username")).thenReturn(true);

        final DeleteUserEvent result = (DeleteUserEvent) deleteUnderTest.update(event);

        assertTrue(result.getSuccess());
    }
    @Test
    void testUpdateEmpty() {
        final Event event = null;

        final DeleteUserEvent result = (DeleteUserEvent) deleteUnderTest.update(event);

        assertNull(result);
    }

    @Test
    void testGetName() {
        final String result = deleteUnderTest.getName();

        assertEquals("user:Delete", result);
    }

    @Test
    void testSetActive() {
        deleteUnderTest.setActive(false);

        assertFalse(deleteUnderTest.isActive());
    }
}
