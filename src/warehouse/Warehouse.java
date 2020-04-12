package warehouse;

import app.EventStream;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;
import user.entity.Customer;
import warehouse.entity.Item;
import user.model.UserManager;
import warehouse.entity.LiquidBulkCargo;
import warehouse.entity.StoragePlace;
import warehouse.errors.UnkownHazardError;
import warehouse.input.NewItemInput;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class Warehouse {
    private String id;
    private ArrayList<StoragePlace> storagePlaces;
    private EventStream eventStream;

    public Warehouse(String id, EventStream eventStream) {
        this.id = id;
        this.eventStream = eventStream;
    }

    public void setUp(int size,int maxValue) {
        this.storagePlaces = new ArrayList<StoragePlace>();
        for (int storagePlaceCounter = 0; storagePlaceCounter < size; storagePlaceCounter ++ ) {
            this.storagePlaces.add(new StoragePlace( this,storagePlaceCounter, new BigDecimal(maxValue)));
        }
    }
    public int store(String jsonItem) throws ParseException {
        // get item type here
        Item item = null;
        NewItemInput newItemInput = new NewItemInput();
        try {
            item = newItemInput.getItem(jsonItem);
        } catch (UnkownHazardError unkownHazardError) {
            return -2;
        }
        return this.store(item);
    }
    public int store(Item item) {
        for (StoragePlace storagePlace : this.storagePlaces) {
            if (item.getValue().compareTo(storagePlace.getLeftSpace()) < 0) {
                storagePlace.setItem(item);
                return storagePlace.getStorageID();
            }
        }
        return -1;
    }
    public Item getItem(String itemID) {
        for (StoragePlace storagePlace : this.storagePlaces) {
            for (Item item : storagePlace.getItems()) {
                if (item.getId().equals(itemID)) {
                    return item;
                }
            }
        }
        return null;
    }
    public ArrayList<Item> getItems(String filterList) {
        ArrayList<Item> filtered = getItems();
    String[] filters = filterList.split(",");
    for(String filterEntry: filters) {
        String[] filterSplit = filterEntry.split(":");
        String filterName = filterSplit[0];
        String filterValue = (filterSplit.length != 2)?"":filterSplit[1];
        switch (filterName) {
            case "hazard":
                if (filterValue == "y") {
                    filtered.removeIf(item -> (item.getHazards() == null));
                    break;
                }
                filtered.removeIf(item -> ((item.getHazards() != null)&& item.getHazards().size() != 0));
                break;
            case "type":
                filtered.removeIf(item -> !item.type.equals(filterValue));
                break;
        }
    }

        return filtered;
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<>();
        for(StoragePlace storagePlace:this.storagePlaces)
        {
            items.addAll(storagePlace.getItems());
        }
        return items;
    }

    public ArrayList<Item> readIteam(StoragePlace storagePlace) {
        return storagePlace.getItems();
    }
    public void updateItem(String itemId, Item newItem) {
        for (StoragePlace storagePlace : this.storagePlaces) {
            for (Item item : storagePlace.getItems()) {
                if (item.getId().equals(itemId)) {
                    if (item instanceof LiquidBulkCargo && newItem instanceof  LiquidBulkCargo) {
                        ((LiquidBulkCargo) item).setPressurized(((LiquidBulkCargo) newItem).isPressurized());
                    }
                    item = newItem;
                }
            }
        }
    }

    public boolean deleteItem(Item item) {
        return true;
    }
}
