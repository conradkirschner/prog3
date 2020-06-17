package app.warehouse.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class DeleteWarehouseEvent implements Event {
    static String name = DeleteWarehouseEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    String id;

    public DeleteWarehouseEvent(String id) {
        this.id = id;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public DeleteWarehouseEvent() {
        this.id = "";
    }

    public String getId() {
        return id;
    }
}
