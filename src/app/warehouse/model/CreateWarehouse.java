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

    private boolean active = true;

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
            Warehouse existing = this.warehouseManager.findWarehouseById(((CreateWarehouseEvent) event).getId());
            if (existing == null) {
                Warehouse warehouse = new Warehouse(((CreateWarehouseEvent) event).getId());
                warehouseManager.newWarehouse(warehouse);
            }
            return null;
        }
        return null;
    }

    @Override
    public String getName() {
        return "warehouse:CreateWarehouse";
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
