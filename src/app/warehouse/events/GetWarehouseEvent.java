package app.warehouse.events;

import app.warehouse.entity.Warehouse;
import famework.event.Event;

import java.util.ArrayList;

public class GetWarehouseEvent implements Event {
    static String name = GetWarehouseEvent.class.getName();

    ArrayList<Warehouse> warehouses;

    public GetWarehouseEvent(ArrayList<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    @Override
    public String getName() {
        return name;
    }
}
