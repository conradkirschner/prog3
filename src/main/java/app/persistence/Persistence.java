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
import app.warehouse.events.StoreItemEvent;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;

import java.io.File;
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
        store.storeAsJOS(users,runDir + savePathUser + ".data");
        store.storeAsJOS(items,runDir + savePathItems+ ".data");
        store.storeAsJOS(warehouseString,runDir + savePathWarehouse + ".data");
    }
    public void saveAsJOB() {
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
        store.storeAsJOB(users,runDir + savePathUser + ".xml");
        store.storeAsJOB(items,runDir + savePathItems + ".xml");
        store.storeAsJOB(warehouseString,runDir + savePathWarehouse + ".xml");
    }
    public boolean loadFromJOS() {
        String runDir = System.getProperty("user.dir");
        byte[] itemBytes = new byte[0];
        byte[] userBytes = new byte[0];
        byte[] warehouseBytes = new byte[0];
        userBytes = readBytes(runDir + savePathUser);
        warehouseBytes = readBytes(runDir + savePathWarehouse);
        itemBytes = readBytes(runDir + savePathItems);


        // bullet proofed loading
        ArrayList<User>  users =(userBytes==null)?new ArrayList<User>(): (ArrayList<User>) load.loadFromJOS(userBytes);
        ArrayList<String>  warehouses =(warehouseBytes==null)?new ArrayList<String>(): (ArrayList<String>) load.loadFromJOS(warehouseBytes);
        ArrayList<Item>  items =(itemBytes==null)?new ArrayList<Item>(): (ArrayList<Item>) load.loadFromJOS(itemBytes);

        for (User user:users) {
            CreateUserEvent createUser = (CreateUserEvent) this.eventHandler.push(new CreateUserEvent(user.getUsername()));
        }
        for (String warehouseId:warehouses) {
            CreateWarehouseEvent createWarehouse = (CreateWarehouseEvent) this.eventHandler.push(new CreateWarehouseEvent(warehouseId));
        }
        for (Item item:items) {
            StoreItemEvent storeItem = (StoreItemEvent) this.eventHandler.push(new StoreItemEvent(item));
        }

        return true;
    }
    public void loadFromJBP() {


    }

    public void saveAsJBP() {

    }

    private byte[] readBytes(String path) {
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(new File(path).toPath());
        } catch (Exception exception) {
            bytes = null;
        }
        return bytes;
    }
}
