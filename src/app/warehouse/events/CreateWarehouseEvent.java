package app.warehouse.events;

import famework.event.Event;

public class CreateWarehouseEvent implements Event {
    static String name = CreateWarehouseEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String username;

    public CreateWarehouseEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public CreateWarehouseEvent setUsername(String username) {
        this.username = username;
        return this;
    }
}
