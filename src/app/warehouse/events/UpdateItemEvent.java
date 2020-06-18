package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import famework.annotation.EventRegister;
import famework.event.Event;

import java.util.ArrayList;

public class UpdateItemEvent implements Event {
    static String name = UpdateItemEvent.class.getName();

    public UpdateItemEvent(ArrayList<Item> items) {
    }

    @Override
    public String getName() {
        return name;
    }
    Item item;
    String id;
    Warehouse warehouse;
    boolean success;


    /**
     * Possible Event settings
     */

    public UpdateItemEvent(String id) {
        this.id = id;
    }
    public UpdateItemEvent(Item item) {
        this.item = item;
    }

    public UpdateItemEvent(Item item, Warehouse warehouse) {
        this.item = item;
        this.warehouse = warehouse;
    }

    public UpdateItemEvent(Item item, Warehouse warehouse, boolean success) {
        this.item = item;
        this.warehouse = warehouse;
        this.success = success;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public UpdateItemEvent() {
        this.item = null;
        this.warehouse = null;
        this.id = null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Item getItem() {
        return item;
    }

    public String getId() {
        return id;
    }

    public boolean getSuccess() {
        return this.success;

    }
}
