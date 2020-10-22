package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.SwapStoragePlace;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;
import famework.event.SubscriberContainer;
import famework.event.SubscriberContainerInterface;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class SwapItem implements Subscriber {

    private boolean active = true;

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new SwapStoragePlace(), 10));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof SwapStoragePlace) {
            String itemIdA = ((SwapStoragePlace) event).getItemIdA();
            String itemIdB = ((SwapStoragePlace) event).getItemIdB();
            String warehouseId = ((SwapStoragePlace) event).getWarehouseId();
            Warehouse warehouse = warehouseManager.findWarehouseById(warehouseId);

            Item itemA = warehouse.getItem(itemIdA);
            Item itemB = warehouse.getItem(itemIdB);

            warehouse.swapItems(itemA, itemB);
        }
        return null;
    }

    @Override
    public String getName() {
        return "warehouse:SwapItem";
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
