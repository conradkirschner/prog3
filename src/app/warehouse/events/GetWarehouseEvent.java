package app.warehouse.events;

import app.warehouse.entity.Warehouse;
import famework.annotation.EventRegister;
import famework.event.Event;

import java.util.ArrayList;

public class GetWarehouseEvent implements Event {
    static String name = GetWarehouseEvent.class.getName();

    String warehouseId = null;
    ArrayList<Warehouse> warehouses;
    Warehouse warehouse;

    public GetWarehouseEvent(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public GetWarehouseEvent(ArrayList<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
    public GetWarehouseEvent(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public GetWarehouseEvent() {
        this.warehouses = null;
    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }


    @Override
    public String getName() {
        return name;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
