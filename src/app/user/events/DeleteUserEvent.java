package app.user.events;

import app.example.TestEvent;
import famework.event.Event;

public class DeleteUserEvent implements Event {
    static String name = TestEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String username;

    public DeleteUserEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
