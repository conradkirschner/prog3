package app.warehouse.model;


import app.warehouse.WarehouseManager;
import app.warehouse.entity.Item;
import app.warehouse.entity.Warehouse;
import app.warehouse.events.GetItemEvent;
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
public class GetItem implements Subscriber {

    @Inject
    WarehouseManager warehouseManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetItemEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof GetItemEvent){
            Item item;
            ArrayList<Item> items = new ArrayList<>();
            ArrayList<Warehouse> warehouses = warehouseManager.getWarehouses();
            for (Warehouse warehouse:warehouses) {
                String type = ((GetItemEvent) event).getType();
                Boolean hazardFilter = ((GetItemEvent) event).isHazardsFilter();
                if (hazardFilter != null) {
                    items.addAll(warehouse.getItems(hazardFilter));
                    continue;
                }
                if (type == null) {
                    items.addAll(warehouse.getItems());
                    continue;
                }
                items.addAll(warehouse.getItems(type));
            }
            return new GetItemEvent(items);
        }
        return null;
    }
}
