package app.warehouse.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class Warehouse implements Serializable {
    public String id;
    public ArrayList<StoragePlace> storagePlaces;

    public Warehouse(String id) {
        this.id = id;
        this.storagePlaces = new ArrayList<>();
        this.storagePlaces.add(new StoragePlace(0, new BigDecimal(100)));
    }

    public Warehouse setId(String id) {
        this.id = id;
        return this;
    }

    public ArrayList<StoragePlace> getStoragePlaces() {
        return storagePlaces;
    }

    public Warehouse setStoragePlaces(ArrayList<StoragePlace> storagePlaces) {
        this.storagePlaces = storagePlaces;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<app.warehouse.entity.Item> updateItem(String id) {
        ArrayList<app.warehouse.entity.Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            app.warehouse.entity.Item found = storagePlace.getItemById(id);
            if(found != null) {
                found.setInspectDate(new Date());
                items.add(found);
                break;
            }
        }
        return items;
    }

    public ArrayList<app.warehouse.entity.Item> getItems() {
        ArrayList<app.warehouse.entity.Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            ArrayList<app.warehouse.entity.Item> found = storagePlace.getItems();
            items.addAll(found);
        }
        return items;
    }

    public Item getItem(String itemId) {
        for(StoragePlace storagePlace: this.storagePlaces) {
            Item item = storagePlace.getItemById(itemId);
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    public StoragePlace getStoragePlaceById(int storagePlaceId) {
        for(StoragePlace storagePlace: this.storagePlaces) {
            if(storagePlace.getStorageID() == storagePlaceId) {
                return storagePlace;
            }

        }
        return null;
    }
    public ArrayList<app.warehouse.entity.Item> getItems(String id) {
        ArrayList<app.warehouse.entity.Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            app.warehouse.entity.Item found = storagePlace.getItemById(id);
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }
    public ArrayList<app.warehouse.entity.Item> getItems(Type type) {
        ArrayList<app.warehouse.entity.Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            app.warehouse.entity.Item found = storagePlace.getItemByType(type.getName());
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }

    public ArrayList<app.warehouse.entity.Item> getItemByType(String type) {
        ArrayList<app.warehouse.entity.Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            app.warehouse.entity.Item found = storagePlace.getItemByType(type);
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }

    public ArrayList<app.warehouse.entity.Item> getItems(boolean type) {
        ArrayList<app.warehouse.entity.Item> items = new ArrayList<>();
        for(StoragePlace storagePlace: this.storagePlaces) {
            app.warehouse.entity.Item found = storagePlace.getItem(type);
            if(found != null) {
                items.add(found);
                break;
            }
        }
        return items;
    }

    public app.warehouse.entity.Item storeItem(Item item) {
        boolean stored = false;
        for (StoragePlace storagePlace: this.storagePlaces) {
            if (storagePlace.getLeftSpace().compareTo(item.getWeight()) >= 0) {
                item.setWarehouse(this.getId());
                item.setStoragePlace(storagePlace.getStorageID());
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

    public app.warehouse.entity.Item swapItems(Item itemA, Item itemB) {
        Integer storagePlaceA = parseInt(itemA.getStoragePlace().toString());
        Integer storagePlaceB = parseInt(itemB.getStoragePlace().toString());

        // check space before Moving
        BigDecimal weightA = itemA.getWeight();
        BigDecimal weightB = itemB.getWeight();
        BigDecimal spaceLeftA = getStoragePlaceById(storagePlaceA).getLeftSpace();
        BigDecimal spaceLeftB =  getStoragePlaceById(storagePlaceB).getLeftSpace();
        BigDecimal newSpaceA = new BigDecimal(0).add(spaceLeftA).add(weightA);
        BigDecimal newSpaceB = new BigDecimal(0).add(spaceLeftB).add(weightB);
        if ( (newSpaceA.compareTo(weightB) > 0 ) && (newSpaceB.compareTo(weightA) > 0 ) ) {
            // possible to swap places
            itemA.setStoragePlace(storagePlaceB);
            itemB.setStoragePlace(storagePlaceA);
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
