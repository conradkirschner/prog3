package app.warehouse.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class SwapStoragePlace implements Event {
    static String name = SwapStoragePlace.class.getName();



    @Override
    public String getName() {
        return name;
    }
    String itemIdA;
    String itemIdB;
    String warehouseId;

    /**
     * Possible Event settings
     */

    public SwapStoragePlace(String itemIdA, String itemIdB, String warehouseId) {
        this.itemIdA = itemIdA;
        this.itemIdB = itemIdB;
        this.warehouseId = warehouseId;
    }


    /**
     * Klick to see subscriber
     */
    @EventRegister
    public SwapStoragePlace() {
        this.itemIdB = null;
        this.itemIdA = null;
    }

    public String getItemIdA() {
        return itemIdA;
    }


    public String getItemIdB() {
        return itemIdB;
    }

    public String getWarehouseId() {
        return warehouseId;
    }
}
