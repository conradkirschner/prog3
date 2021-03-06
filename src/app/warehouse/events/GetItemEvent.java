package app.warehouse.events;

import app.warehouse.entity.Item;
import app.warehouse.entity.Type;
import app.warehouse.entity.Warehouse;
import famework.annotation.EventRegister;
import famework.event.Event;

import java.util.ArrayList;

public class GetItemEvent implements Event {
    static String name = GetItemEvent.class.getName();
    private String id;

    @Override
    public String getName() {
        return name;
    }
    ArrayList<Item> items;
    Warehouse warehouse;
    boolean success;
    Type type;
    Boolean hazardsFilter;



    /**
     * Possible Event settings
     */

    public GetItemEvent(ArrayList<Item> items) {
        this.items = items;
    }

    public GetItemEvent(Boolean hazardsFilter) {
        this.hazardsFilter = hazardsFilter;
    }

    public GetItemEvent(String id) {
        this.id = id;
    }
    public GetItemEvent(Type type) {
        this.type = type;
    }

    public GetItemEvent(ArrayList<Item> items, Warehouse warehouse) {
        this.items = items;
        this.warehouse = warehouse;
    }

    public GetItemEvent(ArrayList<Item> item, Warehouse warehouse, boolean success) {
        this.items = item;
        this.warehouse = warehouse;
        this.success = success;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public GetItemEvent() {
        this.items = null;
        this.warehouse = null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public boolean isSuccess() {
        return success;
    }

    public Boolean isHazardsFilter() {
        return hazardsFilter;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Type getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
