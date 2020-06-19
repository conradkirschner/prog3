package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.DeleteItemEvent;
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
public class DeleteItem implements Subscriber {

    @Inject
    WarehouseManager warehouseManager;

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
            String id = ((DeleteItemEvent) event).getId();
            for (Warehouse warehouse:warehouses) {
                if (warehouse.deleteItem(id)) {
                    deleted = true;
                    break;
                }
            }
            return new DeleteItemEvent(deleted);
        }
        return null;
    }
}
