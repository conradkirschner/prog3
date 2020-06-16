package app.warehouse.model;


import app.warehouse.WarehouseManager;
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

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new StoreItemEvent(null, null), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof StoreItemEvent){
            StoreItemEvent itemEvent = ((StoreItemEvent) event);
            itemEvent.getWarehouse().storeItem(itemEvent.getItem());
            return null;
        }
        return null;
    }
}
