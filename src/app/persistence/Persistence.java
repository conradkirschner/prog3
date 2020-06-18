package app.persistence;

import app.persistence.helper.Load;
import app.persistence.helper.Store;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.CreateWarehouseEvent;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.GetWarehouseEvent;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@Service
public class Persistence {
    String savePathUser = "/";
    String savePathItems = "/";
    String savePathWarehouse = "/";

    @Inject
    Store store;

    @Inject
    Load load;

    public Persistence(ConfigBag configBag) {
        savePathUser = configBag.props.getProperty("user");
        savePathItems = configBag.props.getProperty("items");
        savePathWarehouse = configBag.props.getProperty("warehouse");
    }

    @Inject
    private EventHandler eventHandler;

    public void saveAsJOS() {
        String runDir = System.getProperty("user.dir");

        GetUserEvent getUser = (GetUserEvent) eventHandler.push(new GetUserEvent());
        GetItemEvent getItem = (GetItemEvent) eventHandler.push(new GetItemEvent());
        GetWarehouseEvent getWarehouse = (GetWarehouseEvent) eventHandler.push(new GetWarehouseEvent());
        ArrayList<User>  users = getUser.getUsers();
        ArrayList<Item>  items = getItem.getItems();
        ArrayList<Warehouse>  warehouses = getWarehouse.getWarehouses();
        ArrayList<String> warehouseString = new ArrayList<>();
        for (Warehouse warehouse: warehouses) {
            warehouseString.add(warehouse.getId());
        }
        store.storeAsJOS(users,runDir + savePathUser);
        store.storeAsJOS(items,runDir + savePathItems);
        store.storeAsJOS(warehouseString,runDir + savePathWarehouse);
    }
    public boolean loadFromJOS() {
        String runDir = System.getProperty("user.dir");
        byte[] itemBytes = new byte[0];
        byte[] userBytes = new byte[0];
        byte[] warehouseBytes = new byte[0];
        try {
            itemBytes = Files.readAllBytes(new File(runDir + savePathItems).toPath());
            userBytes = Files.readAllBytes(new File(runDir + savePathUser).toPath());
            warehouseBytes = Files.readAllBytes(new File(runDir + savePathWarehouse).toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        ArrayList<Item>  items = (ArrayList<Item>) load.loadFromJOS(itemBytes);
        ArrayList<User>  users = (ArrayList<User>) load.loadFromJOS(userBytes);
        ArrayList<String>  warehouses = (ArrayList<String>) load.loadFromJOS(warehouseBytes);
        for (User user:users) {
            CreateUserEvent createUser = (CreateUserEvent) this.eventHandler.push(new CreateUserEvent(user.getUsername()));
        }
        for (String warehouseId:warehouses) {
            CreateWarehouseEvent createWarehouse = (CreateWarehouseEvent) this.eventHandler.push(new CreateWarehouseEvent(warehouseId));
        }
        GetWarehouseEvent getWarehouse = (GetWarehouseEvent) this.eventHandler.push(new GetWarehouseEvent());
        ArrayList<Warehouse> selectedWarehouses = getWarehouse.getWarehouses();

        if (selectedWarehouses !=  null) {

            for (Item item:items) {
                for (Warehouse warehouse: selectedWarehouses) {
                    if (warehouse.getId().equals(item.getWarehouse())) {
                        warehouse.storeItem(item);
                    }
                }
            }
        }

        return true;
    }
    public void loadFromJBP() {


    }

    public void saveAsJBP() {

    }
}
