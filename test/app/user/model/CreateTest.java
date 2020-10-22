package app.user.model;

import app.user.UserManager;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTest {

    private Create createUnderTest;

    @BeforeEach
    void setUp() {
        createUnderTest = new Create();
        createUnderTest.userManager = mock(UserManager.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = createUnderTest.getSubscribedEvents();
        assertEquals(result.get(0).getSubscribedEvent().getClass(), CreateUserEvent.class);

    }

    @Test
    void testUpdate() {
        final CreateUserEvent event = mock(CreateUserEvent.class);
        when(event.getUsername()).thenReturn("test");

        final CreateUserEvent result = (CreateUserEvent) createUnderTest.update(event);

        assertTrue(result.getStatus());
        verify(createUnderTest.userManager).newUser(any(User.class));
        verify(createUnderTest.userManager).newUser(any(User.class));
    }
    @Test
    void testUpdateEmptyEvent() {
        final CreateUserEvent event = null;

        final CreateUserEvent result = (CreateUserEvent) createUnderTest.update(event);

        assertNull(result);
    }

    @Test
    void testGetName() {

        final String result = createUnderTest.getName();

        assertEquals("user:Create", result);
    }

    @Test
    void testSetActive() {
        createUnderTest.setActive(false);

        assertFalse(createUnderTest.isActive());
    }
}
