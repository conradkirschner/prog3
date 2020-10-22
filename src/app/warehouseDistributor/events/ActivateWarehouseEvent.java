package app.warehouseDistributor.events;

import app.warehouse.events.CreateWarehouseEvent;
import famework.annotation.EventRegister;
import famework.event.Event;

import java.util.ArrayList;

public class ActivateWarehouseEvent implements Event {
    static String name = CreateWarehouseEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    ArrayList<String> warehouseIds;

    @EventRegister
    public ActivateWarehouseEvent(ArrayList<String> warehouseIds) {
        this.warehouseIds = warehouseIds;
    }

    /**
     * Klick to see subscriber
     */



    public ArrayList<String> getWarehouseIds() {
        return warehouseIds;
    }
}
