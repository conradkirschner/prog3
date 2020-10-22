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
    boolean success;

    public DeleteUserEvent(boolean success) {
        this.success = success;
    }

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

    public boolean getSuccess() {
        return success;
    }
}
