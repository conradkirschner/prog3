package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.DeleteItemEvent;
import app.warehouse.events.GetItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class DeleteItem implements Subscriber {

    private boolean active = true;
    @Inject
    WarehouseManager warehouseManager;

    @Inject
    EventHandler eventHandler;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new DeleteItemEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof DeleteItemEvent) {
            boolean deleted = false;
            ArrayList<Warehouse> warehouses = warehouseManager.getWarehouses();
            String id = ((DeleteItemEvent) event).getId().substring(1);
            for (Warehouse warehouse:warehouses) {
                GetItemEvent getItemEvent = (GetItemEvent) eventHandler.push(new GetItemEvent(id));
                if (getItemEvent.getItems().size() == 0) return event;
                if (warehouse.deleteItem(id)) {
                    deleted = true;
                    break;
                }
            }
            return new DeleteItemEvent(deleted);
        }
        return null;
    }


    @Override
    public String getName() {
        return "warehouse:DeleteItem";
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
