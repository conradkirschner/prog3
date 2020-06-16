package app.warehouse;

import app.warehouse.entity.Warehouse;
import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.util.ArrayList;

@Service
public class WarehouseManager {

    public ConfigBag config;

    private ArrayList<Warehouse> warehouses;

    public WarehouseManager(ConfigBag test) {
        this.config = test;
        this.warehouses = new ArrayList<>();

    }

    public ArrayList<Warehouse> getUser() {
        return warehouses;
    }

    public void newUser(Warehouse warehouse) {
        this.warehouses.add(warehouse);
    }

    public void removeUser(String username) {
      this.warehouses.remove(findUserByName(username));
    }

    private Warehouse findUserByName(String username) {
        Warehouse found = null;
        for (Warehouse warehouse : this.warehouses) {
            if (warehouse.getUsername().equals(username)) {
                found= warehouse;
            }
        }
        return found;
    }
}
