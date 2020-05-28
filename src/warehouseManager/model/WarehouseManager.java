package warehouseManager.model;

import app.EventStream;
import warehouse.model.Warehouse;
import warehouseManager.entity.AllWarehouses;

import java.util.ArrayList;

public class WarehouseManager {
    private ArrayList<Warehouse> warehouses;
    private EventStream eventStream;

    public WarehouseManager(EventStream eventStream) {
        this.eventStream = eventStream;
        this.warehouses = new ArrayList<Warehouse>();

    }

    public Warehouse getWarehouse(String id) {
        for( Warehouse warehouse: warehouses )
        {
            if(warehouse.getWarehouseName().equals(id)) {
                return warehouse;
            }
        }
        return null;
    }

    public void addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    public AllWarehouses getWarehouses() {
        return new AllWarehouses(warehouses);
    }
}
