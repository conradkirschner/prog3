package app.warehouse.events;

import famework.annotation.EventRegister;
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

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public CreateWarehouseEvent() {
        this.id = "";
    }

    public String getId() {
        return id;
    }

    public CreateWarehouseEvent setId(String id) {
        this.id = id;
        return this;
    }
}
