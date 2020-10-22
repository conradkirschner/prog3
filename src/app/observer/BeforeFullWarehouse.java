package app.observer;


import app.warehouse.entity.StoragePlace;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.GetWarehouseEvent;
import app.warehouse.events.StoreItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class BeforeFullWarehouse implements Subscriber, PropertyChangeListener {
    private StorageSize storageSize;

    @Inject
    PrintStream printStream;

    @Inject
    private EventHandler eventHandler;

    public BeforeFullWarehouse(PrintStream printStream, EventHandler eventHandler) {
        this.printStream = printStream;
        this.eventHandler = eventHandler;
        storageSize = new StorageSize();
        storageSize.addPropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        BigDecimal size = storageSize.getGetStorageLimit();
        if (size.compareTo(new BigDecimal(10)) <= 0) {
            printStream.println("[Storage Limit reached]" + storageSize.getGetStorageLimit().toString());

        }
    }

    private boolean active = true;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new StoreItemEvent(), 1000));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof StoreItemEvent){
            ArrayList<Warehouse> warehouses = ((GetWarehouseEvent) this.eventHandler.push(new GetWarehouseEvent())).getWarehouses();
            for (Warehouse warehouse: warehouses){
                ArrayList<StoragePlace> storagePlaces = warehouse.getStoragePlaces();
                for (StoragePlace storagePlace: storagePlaces){
                    storageSize.setStorageLimit(storagePlace.getLeftSpace());
                }
            }
            }
        return event;
    }

    @Override
    public String getName() {
        return "observer:BeforeFullWarehouse";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
