package app.cli.model;

import app.cli.CliManager;
import app.user.events.CreateUserEvent;
import famework.event.Event;
import famework.event.SubscriberContainerInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserTest {

    private CreateUser createUserUnderTest;

    @BeforeEach
    void setUp() {
        createUserUnderTest = new CreateUser();
        createUserUnderTest.cliManager = mock(CliManager.class);
    }

    @Test
    void testGetSubscribedEvents() {
        final ArrayList<SubscriberContainerInterface> result = createUserUnderTest.getSubscribedEvents();

        assertEquals(result.get(0).getSubscribedEvent().getClass(), CreateUserEvent.class);
    }

    @Test
    void testUpdateSuccess() {
        final Event event = new CreateUserEvent("test", true);

        final Event result = createUserUnderTest.update(event);


        verify(createUserUnderTest.cliManager).setFlashMessage( "Kunde: \"test\" - erfolgreich gespeichert!");
        verify(createUserUnderTest.cliManager).setPreviousScreen();
        verify(createUserUnderTest.cliManager, never()).showError("string");
    }
    @Test
    void testUpdateFailure() {
        final Event event = new CreateUserEvent("test", false);

        final Event result = createUserUnderTest.update(event);

        verify(createUserUnderTest.cliManager,  never()).setFlashMessage( "Kunde: \"test\" - erfolgreich gespeichert!");
        verify(createUserUnderTest.cliManager,  never()).setPreviousScreen();
        verify(createUserUnderTest.cliManager).showError( "Kunde: \"test\" konnte nicht angelegt werden");
    }

    @Test
    void testGetName() {
        final String result = createUserUnderTest.getName();

        assertEquals("cli:CreateUser", result);
    }
    @Test
    void testSetActive() {
        createUserUnderTest.setActive(false);

        assertFalse(createUserUnderTest.isActive());
    }
}
