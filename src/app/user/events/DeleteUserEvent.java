package app.user.events;

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

    public String getUsername() {
        return username;
    }
}
