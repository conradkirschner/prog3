package app.warehouse.events;

import famework.event.Event;

public class DeleteWarehouseEvent implements Event {
    static String name = DeleteWarehouseEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String username;

    public DeleteWarehouseEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
