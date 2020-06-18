package app.warehouse.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

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

    public ArrayList<Item> updateItem(String id) {
        ArrayList<Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            Item found = storagePlace.getItemById(id);
            if(found != null) {
                found.setInspectDate(new Date());
                items.add(found);
                break;
            }
        }
        return items;
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            ArrayList<Item> found = storagePlace.getItems();
            items.addAll(found);
        }
        return items;
    }
    public ArrayList<Item> getItems(String type) {
        ArrayList<Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            Item found = storagePlace.getItemByType(type);
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }

    public ArrayList<Item> getItemByType(String type) {
        ArrayList<Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            Item found = storagePlace.getItemByType(type);
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }

    public ArrayList<Item> getItems(boolean type) {
        ArrayList<Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            Item found = storagePlace.getItem(type);
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }

    public Item storeItem(Item item) {
        boolean stored = false;
        for (StoragePlace storagePlace: this.storagePlaces) {
            if (storagePlace.getLeftSpace().compareTo(item.weight) >= 0) {
                item.setWarehouse(this.getId());
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

    public boolean deleteItem(String id) {
        for(StoragePlace storagePlace: this.storagePlaces) {
            boolean found = storagePlace.removeItem(id);
            if (found) {
                return true;
            }
        }
        return false;
    }
}
