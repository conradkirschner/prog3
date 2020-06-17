package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import famework.annotation.EventRegister;
import famework.event.Event;

public class StoreItemEvent implements Event {
    static String name = StoreItemEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    Item item;
    String inputString;
    Warehouse warehouse;
    boolean success;


    /**
     * Possible Event settings
     */

    public StoreItemEvent(String inputString) {
          this.inputString = inputString;
    }
    public StoreItemEvent(Item item) {
        this.item = item;
    }

    public StoreItemEvent(Item item, Warehouse warehouse) {
        this.item = item;
        this.warehouse = warehouse;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public StoreItemEvent() {
        this.item = null;
        this.warehouse = null;
        this.inputString = null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
    public boolean isSuccess() {
        return success;
    }

    public StoreItemEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    public Item getItem() {
        return item;
    }

    public StoreItemEvent setItem(Item item) {
        this.item = item;
        return this;
    }

    public boolean getSuccess() {
        return this.success;

    }
}
