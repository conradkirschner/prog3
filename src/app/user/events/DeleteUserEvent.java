package app.user.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class DeleteUserEvent implements Event {
    static String name = DeleteUserEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String username;

    public DeleteUserEvent(String username) {
        this.username = username;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public DeleteUserEvent() {
        this.username = null;
    }

    public String getUsername() {
        return username;
    }
}
