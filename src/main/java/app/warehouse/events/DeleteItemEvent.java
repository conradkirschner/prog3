package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import famework.annotation.EventRegister;
import famework.event.Event;

import java.util.ArrayList;

public class DeleteItemEvent implements Event {
    static String name = DeleteItemEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    ArrayList<Item> items;
    Warehouse warehouse;
    boolean success;
    String id;



    /**
     * Possible Event settings
     */


    public DeleteItemEvent(String id) {
        this.id = id;
    }
    public DeleteItemEvent(boolean success) {
        this.success = success;
    }

    public DeleteItemEvent(ArrayList<Item> items, Warehouse warehouse) {
        this.items = items;
        this.warehouse = warehouse;
    }

    public DeleteItemEvent(ArrayList<Item> item, Warehouse warehouse, boolean success) {
        this.items = item;
        this.warehouse = warehouse;
        this.success = success;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public DeleteItemEvent() {
        this.items = null;
        this.warehouse = null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public boolean getSuccess() {
        return success;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getId() {
        return id;
    }
}
