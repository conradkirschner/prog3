package app.user.events;

import app.example.TestEvent;
import famework.event.Event;

public class CreateUserEvent implements Event {
    static String name = TestEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String username;

    public CreateUserEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public CreateUserEvent setUsername(String username) {
        this.username = username;
        return this;
    }
}
