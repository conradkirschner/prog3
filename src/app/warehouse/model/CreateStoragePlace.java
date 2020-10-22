package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.StoragePlace;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.CreateStoragePlaceEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;
import famework.event.SubscriberContainer;
import famework.event.SubscriberContainerInterface;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class CreateStoragePlace implements Subscriber {

    private boolean active = true;

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CreateStoragePlaceEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof CreateStoragePlaceEvent){
            String warehouseId = ((CreateStoragePlaceEvent) event).getWarehouseId();
            int storageSize = ((CreateStoragePlaceEvent) event).getSpace();
            int storagePlaceNumber = ((CreateStoragePlaceEvent) event).getStoragePlaceNumber();
            Warehouse warehouse = warehouseManager.findWarehouseById(warehouseId);
            if (warehouse == null) {
                return null;
            }
            warehouse.storagePlaces.add(new StoragePlace(storagePlaceNumber, new BigDecimal(storageSize)));
            return null;
        }
        return null;
    }

    @Override
    public String getName() {
        return "warehouse:CreateStoragePlace";
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
