package warehouse;

import app.EventStream;
import storageContract.cargo.Cargo;
import storageContract.cargo.Hazard;
import user.entity.Customer;
import warehouse.entity.Item;
import user.model.UserManager;
import warehouse.entity.LiquidBulkCargo;
import warehouse.entity.StoragePlace;

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

    public void setUp(int size) {
        this.storagePlaces = new ArrayList<StoragePlace>();
        for (int storagePlaceCounter = 0; storagePlaceCounter < size; storagePlaceCounter ++ ) {
            this.storagePlaces.add(new StoragePlace( this,storagePlaceCounter, new BigDecimal(5)));

        }
    }
    public int store(String jsonItem) throws ParseException {
        LiquidBulkCargo liquidBulkCargo = new LiquidBulkCargo(
                new BigDecimal(1000000),
                (storageContract.administration.Customer) this.eventStream.pushData("user:get", "0"),
                Collections.singleton(Hazard.radioactive),
                ZonedDateTime.now()
        );
        liquidBulkCargo.restoreFromJson(jsonItem);
        return this.store(liquidBulkCargo);
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

    public ArrayList<Item> readIteam(StoragePlace storagePlace) {
        return storagePlace.getItems();
    }
    public boolean updateItem(Item item) {
        return true;
    }

    public boolean deleteItem(Item item) {
        return true;
    }
}
