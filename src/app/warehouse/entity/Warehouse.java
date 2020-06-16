package app.warehouse.entity;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Warehouse {
    private String id;
    private ArrayList<StoragePlace> storagePlaces;

    public Warehouse(String id) {
        this.id = id;
        this.storagePlaces = new ArrayList<>();
        this.storagePlaces.add(new StoragePlace(100, new BigDecimal(100)));
        this.storagePlaces.add(new StoragePlace(101, new BigDecimal(100)));
        this.storagePlaces.add(new StoragePlace(102, new BigDecimal(100)));
    }

    public String getId() {
        return this.id;
    }

    public Item storeItem(Item item) {
        boolean stored = false;
        for (StoragePlace storagePlace: this.storagePlaces) {
            if (storagePlace.getLeftSpace().compareTo(item.weight) <= 0) {
                storagePlace.setItem(item);
                stored=true;
                break;
            }
        }
        if (stored) {
            return item;
        }
        return null;
    }
}
