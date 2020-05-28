package warehouse.model;

import app.EventStream;

import warehouse.entity.Item;
import warehouse.entity.StoragePlace;
import warehouse.errors.UnkownHazardError;
import cli.validators.NewItemInput;
import warehouse.thread.InsertThread;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Warehouse extends Thread{
    private String id;
    private ArrayList<StoragePlace> storagePlaces;
    private EventStream eventStream;

    private InsertThread insertThread;


    public Warehouse(String id, EventStream eventStream) {
        this.id = id;
        this.eventStream = eventStream;
        this.storagePlaces = new ArrayList<StoragePlace>();
        insertThread = new InsertThread(this);
        insertThread.start();

    }
    public String getWarehouseName() {
        return id;
    }

    public void setUp(int size, int maxValue) {
        this.storagePlaces = new ArrayList<StoragePlace>();
        for (int storagePlaceCounter = 0; storagePlaceCounter < size; storagePlaceCounter ++ ) {
            this.storagePlaces.add(new StoragePlace( this,storagePlaceCounter, new BigDecimal(maxValue)));
        }
    }

    public int storeParallel(Item item) throws ParseException {
        synchronized(this.insertThread) {
            if (this.insertThread.getState().name().equals("WAITING")) {
                this.insertThread.setItem(item);
                this.insertThread.notify();
                return 0;
            }
        }
        // Thread failed, return failure and restart
        synchronized(this.insertThread) {
            if (this.insertThread.getState().name().equals("TERMINATED")) {
                this.insertThread = new InsertThread(this);
                this.insertThread.start();
                return -1;
            }
        }
        return -1;
    }

    public int store(String jsonItem) throws ParseException {
        // get item type here
        Item item = null;
        NewItemInput newItemInput = new NewItemInput(this.eventStream);
        try {
            item = newItemInput.getItem(jsonItem);
            if (item == null) {
                return -3;
            }
        } catch (UnkownHazardError unkownHazardError) {
            return -2;
        }
        return this.store(item);
    }
    public int store(Item item) {
        for (StoragePlace storagePlace : this.storagePlaces) {
            if (item.getValue().compareTo(storagePlace.getLeftSpace()) <= 0) {
                storagePlace.setItem(item);
                return 1;
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
                if (filterValue.equals("y")) {
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

    public void updateItem(String itemId) {
        for (StoragePlace storagePlace : this.storagePlaces) {
            for (Item item : storagePlace.getItems()) {
                if (item.getId().equals(itemId)) {
                   item.setInspectDate(new Date());
                }
            }
        }
    }

    public boolean deleteItem(String itemId) {
        Boolean success = false;
        for (StoragePlace storagePlace : this.storagePlaces) {
            success = (success) || storagePlace.removeItem(itemId);
        }
        return success;
    }
}
