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
    Boolean status;

    public CreateUserEvent(String username) {
        this.username = username;
    }
    public CreateUserEvent(String username, boolean status) {
        this.username = username;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public CreateUserEvent setUsername(String username) {
        this.username = username;
        return this;
    }

    public void setName(String name) {
        CreateUserEvent.name = name;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
