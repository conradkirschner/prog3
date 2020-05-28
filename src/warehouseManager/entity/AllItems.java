package warehouseManager.entity;

import app.events.Event;
import warehouse.entity.Item;

import java.util.ArrayList;

public class AllItems implements Event {
    private ArrayList<Item> itemArrayList;

    public AllItems(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    public ArrayList<Item> getItems() {
        return itemArrayList;
    }
}
