package app.warehouseDistributor;

import app.warehouse.entity.Warehouse;
import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.util.ArrayList;

@Service
public class WarehouseDistributor {

    public ConfigBag config;

    private ArrayList<Warehouse> warehouses;

    public WarehouseDistributor(ConfigBag config) {
        this.config = config;
        this.warehouses = new ArrayList<>();

    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void newWarehouse(Warehouse warehouse) {
        if (findWarehouseById(warehouse.getId()) != null) {
            return;
        }
        this.warehouses.add(warehouse);
    }

    public void removeWarehouse(String warehouseId) {
      this.warehouses.remove(findWarehouseById(warehouseId));
    }

    public Warehouse findWarehouseById(String warehouseId) {
        Warehouse found = null;
        for (Warehouse warehouse : this.warehouses) {
            if (warehouse.getId().equals(warehouseId)) {
                found= warehouse;
            }
        }
        return found;
    }
}
