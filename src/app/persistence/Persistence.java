package app.persistence;

import app.persistence.helper.LoadService;
import app.persistence.helper.StoreService;
import app.user.entity.User;
import app.user.entity.UserList;
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
import java.util.List;

@Service
public class Persistence {
    String savePathUser = "/";
    String savePathItems = "/";
    String savePathWarehouse = "/";
    @Inject
    private LoadService loadService;
    @Inject
    private StoreService storeService;
    public Persistence(LoadService loadService,EventHandler eventHandler, StoreService storeService, ConfigBag configBag) throws Exception {
        savePathUser = configBag.props.getProperty("user");
        savePathItems = configBag.props.getProperty("items");
        savePathWarehouse = configBag.props.getProperty("warehouse");
        if (loadService == null || storeService == null) {
            throw new Exception("");
        }
        this.loadService = loadService;
        this.storeService = storeService;
        this.eventHandler = eventHandler;
    }

    @Inject
    private EventHandler eventHandler;

    public void saveAsJOS() {
        String runDir = System.getProperty("user.dir");

        GetUserEvent getUser = (GetUserEvent) eventHandler.push(new GetUserEvent());
        GetItemEvent getItem = (GetItemEvent) eventHandler.push(new GetItemEvent());
        GetWarehouseEvent getWarehouse = (GetWarehouseEvent) eventHandler.push(new GetWarehouseEvent());
        ArrayList<User>  users = getUser.getUsers();
        UserList userList = new UserList();
        userList.setUserList(users);
        ArrayList<Item>  items = getItem.getItems();
        ArrayList<Warehouse>  warehouses = getWarehouse.getWarehouses();
        ArrayList<String> warehouseString = new ArrayList<>();
        for (Warehouse warehouse: warehouses) {
            warehouseString.add(warehouse.getId());
        }
        storeService.storeAsJOS(userList,runDir + savePathUser + ".data");
        storeService.storeAsJOS(items,runDir + savePathItems+ ".data");
        storeService.storeAsJOS(warehouseString,runDir + savePathWarehouse + ".data");
    }

    public boolean loadFromJOS() {
        byte[] itemBytes = new byte[0];
        byte[] userBytes = new byte[0];
        byte[] warehouseBytes = new byte[0];
        String runDir = System.getProperty("user.dir");
        userBytes = readBytes(runDir + savePathUser + ".data");
        warehouseBytes = readBytes(runDir + savePathWarehouse + ".data");
        itemBytes = readBytes(runDir + savePathItems + ".data");


        // bullet proofed loading
        List<User> users = null;
        ArrayList<String> warehouses = null;
        ArrayList<Item> items = null;
        try {
            users =(userBytes==null)?new ArrayList<User>(): ((UserList) loadService.loadFromJOS(userBytes)).getUserList();
            warehouses =(warehouseBytes==null)?new ArrayList<String>(): (ArrayList<String>) loadService.loadFromJOS(warehouseBytes);
            items =(itemBytes==null)?new ArrayList<Item>(): (ArrayList<Item>) loadService.loadFromJOS(itemBytes);

        } catch (Exception e) {

        }


        restoreEvents(users, warehouses, items);
        return true;
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

    /**
     * restore with new storage places
     * @param users
     * @param warehouses
     * @param items
     */
    private void restoreEvents(List<User> users, ArrayList<String> warehouses, ArrayList<Item> items) {
        if (users == null) {
            return;
        }
        for (User user:users) {
            this.eventHandler.push(new CreateUserEvent(user.getUsername()));
        }
        if (warehouses == null) {
            return;
        }
        for (String warehouseId:warehouses) {
           this.eventHandler.push(new CreateWarehouseEvent(warehouseId));
        }
        if (items == null) {
            return;
        }
        for (Item item:items) {
            this.eventHandler.push(new StoreItemEvent(item));
        }
    }
}
