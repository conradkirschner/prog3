package app.warehouse;

import app.warehouse.entity.Warehouse;
import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.util.ArrayList;

@Service
public class WarehouseManager {

    public ConfigBag config;

    private ArrayList<Warehouse> warehouses;

    public WarehouseManager(ConfigBag config) {
        this.config = config;
        this.warehouses = new ArrayList<>();

    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void newWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    public void removeWarehouse(String warehouseId) {
      this.warehouses.remove(findWarehouseById(warehouseId));
    }

    private Warehouse findWarehouseById(String warehouseId) {
        Warehouse found = null;
        for (Warehouse warehouse : this.warehouses) {
            if (warehouse.getId().equals(warehouseId)) {
                found= warehouse;
            }
        }
        return found;
    }
}
