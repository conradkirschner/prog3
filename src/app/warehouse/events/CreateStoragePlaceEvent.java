package app.warehouse.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class CreateStoragePlaceEvent implements Event {
    static String name = CreateWarehouseEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    int space;
    int storagePlaceNumber;
    String warehouseId;

    public CreateStoragePlaceEvent(int space, int storagePlaceNumber, String warehouseId) {
        this.space = space;
        this.storagePlaceNumber = storagePlaceNumber;
        this.warehouseId = warehouseId;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public CreateStoragePlaceEvent() {
        this.space = -1;
    }

    public int getSpace() {
        return space;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int getStoragePlaceNumber() {
        return storagePlaceNumber;
    }
}
