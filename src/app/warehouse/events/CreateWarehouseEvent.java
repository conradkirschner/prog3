package app.warehouse.events;

import famework.event.Event;

public class CreateWarehouseEvent implements Event {
    static String name = CreateWarehouseEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String id;

    public CreateWarehouseEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public CreateWarehouseEvent setId(String id) {
        this.id = id;
        return this;
    }
}
