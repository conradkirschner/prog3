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
    private Item item;
    private Warehouse warehouse;
    private boolean success;


    /**
     * Possible Event settings
     */

    public StoreItemEvent(Item item) {
        this.item = item;
        this.warehouse = null;
    }

    public StoreItemEvent(Item item, Warehouse warehouse) {
        this.item = item;
        this.warehouse = warehouse;
    }

    public StoreItemEvent(Item item, Warehouse warehouse, boolean success) {
        this.item = item;
        this.warehouse = warehouse;
        this.success = success;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public StoreItemEvent() {
        this.item = null;
        this.warehouse = null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
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


    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
