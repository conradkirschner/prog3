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
            StoreItemEvent response = new StoreItemEvent(itemEvent.getItem(), itemEvent.getWarehouse());
            Item item = (Item) itemEvent.getItem();
            Warehouse selectedWarehouse = itemEvent.getWarehouse();

            if (selectedWarehouse != null) {
                return new StoreItemEvent(selectedWarehouse.storeItem(itemEvent.getItem()), itemEvent.getWarehouse());
            }

            ArrayList<Warehouse> warehouses = warehouseManager.getWarehouses();
            for (Warehouse warehouse:warehouses) {
                if(item.getWarehouse() != null && selectedWarehouse == null) {
                    if (item.getWarehouse().equals(warehouse.getId())) {
                        Item stored = warehouse.storeItem(itemEvent.getItem());
                        return new StoreItemEvent(stored, warehouse, true);

                    }
                    continue;
                }
                Item stored = warehouse.storeItem(itemEvent.getItem());
                if (stored != null) {
                   return new StoreItemEvent(stored, warehouse, true);
                }
            }
            return null;
        }
        return null;
    }
}
