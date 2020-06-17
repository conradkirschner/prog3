package app.user.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class CreateUserEvent implements Event {
    static String name = CreateUserEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String username;

    public CreateUserEvent(String username) {
        this.username = username;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public CreateUserEvent() {
        this.username = null;
    }

    public String getUsername() {
        return username;
    }

    public CreateUserEvent setUsername(String username) {
        this.username = username;
        return this;
    }
}
