package warehouseManager.entity;

import app.events.Event;
import warehouse.model.Warehouse;

import java.util.ArrayList;

public class AllWarehouses implements Event {
    private ArrayList<Warehouse> warehouseArrayList;

    public AllWarehouses(ArrayList<Warehouse> warehouseArrayList) {
        this.warehouseArrayList = warehouseArrayList;
    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouseArrayList;
    }

}
