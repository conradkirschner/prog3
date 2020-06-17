package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import famework.event.Event;

public class StoreItemEvent implements Event {
    static String name = StoreItemEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
    Item item;
    Warehouse warehouse;
    boolean success;



    public StoreItemEvent(Item item, Warehouse warehouse) {
        this.item = item;
        this.warehouse = warehouse;
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
