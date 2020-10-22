package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.StoreItemEvent;
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
public class StoreItem implements Subscriber {

    private boolean active = true;

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new StoreItemEvent(), 10));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof StoreItemEvent) {
            StoreItemEvent itemEvent = ((StoreItemEvent) event);
                Item item = (Item) itemEvent.getItem();
            if (item != null && itemEvent.getWarehouse() != null) {

                Warehouse selectedWarehouse = warehouseManager.findWarehouseById(itemEvent.getWarehouse().getId());

                if (selectedWarehouse != null) {
                    Item stored = selectedWarehouse.storeItem(itemEvent.getItem());
                    return new StoreItemEvent(stored, selectedWarehouse, true);
                }
            }


            return event;
        }
        return event;
    }

    @Override
    public String getName() {
        return "warehouse:StoreItem";
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
