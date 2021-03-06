package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.events.GetWarehouseEvent;
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
public class GetWarehouse implements Subscriber {

    private boolean active = true;

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetWarehouseEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof GetWarehouseEvent){

            if (((GetWarehouseEvent) event).getWarehouseId() != null) {
                return new GetWarehouseEvent(warehouseManager.findWarehouseById(((GetWarehouseEvent) event).getWarehouseId()));
            }
            return new GetWarehouseEvent(warehouseManager.getWarehouses());
        }
        return null;
    }


    @Override
    public String getName() {
        return "warehouse:GetWarehouse";
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
