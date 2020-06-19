package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.CreateWarehouseEvent;
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
public class CreateWarehouse implements Subscriber {

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CreateWarehouseEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof CreateWarehouseEvent){
            Warehouse warehouse = new Warehouse(((CreateWarehouseEvent) event).getId());
            warehouseManager.newWarehouse(warehouse);
            return null;
        }
        return null;
    }
}
