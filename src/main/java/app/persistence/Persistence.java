package app.persistence;

import app.persistence.helper.Load;
import app.persistence.helper.Store;
import app.user.entity.User;
import app.user.entity.UserList;
import app.user.events.CreateUserEvent;
import app.user.events.GetUserEvent;
import app.warehouse.entity.Item;
import app.warehouse.entity.ItemList;
import app.warehouse.entity.Warehouse;
import app.warehouse.entity.WarehouseList;
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

    public boolean loadFromJOS() {
        byte[] itemBytes = new byte[0];
        byte[] userBytes = new byte[0];
        byte[] warehouseBytes = new byte[0];
        String runDir = System.getProperty("user.dir");
        userBytes = readBytes(runDir + savePathUser);
        warehouseBytes = readBytes(runDir + savePathWarehouse);
        itemBytes = readBytes(runDir + savePathItems);


        // bullet proofed loading
        ArrayList<User>  users =(userBytes==null)?new ArrayList<User>(): (ArrayList<User>) load.loadFromJOS(userBytes);
        ArrayList<String>  warehouses =(warehouseBytes==null)?new ArrayList<String>(): (ArrayList<String>) load.loadFromJOS(warehouseBytes);
        ArrayList<Item>  items =(itemBytes==null)?new ArrayList<Item>(): (ArrayList<Item>) load.loadFromJOS(itemBytes);


        restoreEvents(users, warehouses, items);
        return true;
    }


    public void loadFromJBP() {
        String runDir = System.getProperty("user.dir");
        ArrayList<User> users = (ArrayList<User>) load.loadFromJBP(runDir + savePathUser + ".xml", User.class);
        ArrayList<String> warehouses = (ArrayList<String>) load.loadFromJBP(runDir + savePathWarehouse+ ".xml",  Warehouse.class);
        ArrayList<Item> items = (ArrayList<Item>) load.loadFromJBP(runDir + savePathItems+ ".xml",  Item.class);
        restoreEvents(users, warehouses, items);
    }

    public void saveAsJBP() {
        String runDir = System.getProperty("user.dir");

        GetUserEvent getUser = (GetUserEvent) eventHandler.push(new GetUserEvent());
        GetItemEvent getItem = (GetItemEvent) eventHandler.push(new GetItemEvent());
        GetWarehouseEvent getWarehouse = (GetWarehouseEvent) eventHandler.push(new GetWarehouseEvent());
        ArrayList<User>  users = getUser.getUsers();
        UserList userList = new UserList();
        userList.setUserList(users);

        ArrayList<Item>  items = getItem.getItems();
        ItemList itemList = new ItemList();
        itemList.setItemList(items);
        ArrayList<Warehouse>  warehouses = getWarehouse.getWarehouses();
        WarehouseList warehouseList = new WarehouseList();
        warehouseList.setWarehouseList(warehouses);
        ArrayList<String> warehouseString = new ArrayList<>();
        for (Warehouse warehouse: warehouses) {
            warehouseString.add(warehouse.getId());
        }
        store.storeAsJBP(userList,runDir + savePathUser + ".xml");
        store.storeAsJBP(warehouseList,runDir + savePathWarehouse + ".xml");
        store.storeAsJBP(itemList,runDir + savePathItems + ".xml");

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

    private void restoreEvents(ArrayList<User> users, ArrayList<String> warehouses, ArrayList<Item> items) {
        for (User user:users) {
            CreateUserEvent createUser = (CreateUserEvent) this.eventHandler.push(new CreateUserEvent(user.getUsername()));
        }
        for (String warehouseId:warehouses) {
            CreateWarehouseEvent createWarehouse = (CreateWarehouseEvent) this.eventHandler.push(new CreateWarehouseEvent(warehouseId));
        }
        for (Item item:items) {
            StoreItemEvent storeItem = (StoreItemEvent) this.eventHandler.push(new StoreItemEvent(item));
        }
    }
}
