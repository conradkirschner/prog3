package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.UpdateItemEvent;
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
public class UpdateItem implements Subscriber {

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new UpdateItemEvent(), 10));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof UpdateItemEvent){
            ArrayList<Item> items = new ArrayList<>();
            ArrayList<Warehouse> warehouses = warehouseManager.getWarehouses();
            for (Warehouse warehouse:warehouses) {
                String id = ((UpdateItemEvent) event).getId();
                if (id == null) {
                    continue;
                }
                items.addAll(warehouse.updateItem(id));
            }
            return new UpdateItemEvent(items);
        }
        return null;
    }
}
