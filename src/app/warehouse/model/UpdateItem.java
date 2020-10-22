package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.GetItemEvent;
import app.warehouse.events.UpdateItemEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class UpdateItem implements Subscriber {

    private boolean active = true;

    @Inject
    WarehouseManager warehouseManager;

    @Inject
    EventHandler eventHandler;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new UpdateItemEvent(), 10));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof UpdateItemEvent) {
            if (((UpdateItemEvent) event).getId() == null) return event;
            GetItemEvent getItemEvent = (GetItemEvent) eventHandler.push(new GetItemEvent(((UpdateItemEvent) event).getId().substring(1)));
            if (getItemEvent == null) {
                return event;
            }
            ArrayList<Item> items = new ArrayList<>();
            ArrayList<Warehouse> warehouses = warehouseManager.getWarehouses();
            for (Warehouse warehouse:warehouses) {
                if (getItemEvent.getItems().get(0).getWarehouse().equals(warehouse.id)) {
                    String id = ((UpdateItemEvent) event).getId().substring(1);;
                    if (id == null) {
                        continue;
                    }
                    items.addAll(warehouse.updateItem(id));
                }

            }
            return new UpdateItemEvent(items);
        }
        return null;
    }

    @Override
    public String getName() {
        return "warehouse:UpdateItem";
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
