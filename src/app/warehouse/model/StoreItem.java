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
        events.add(new SubscriberContainer(new StoreItemEvent(null, null), 10));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof StoreItemEvent) {
            StoreItemEvent itemEvent = ((StoreItemEvent) event);
            StoreItemEvent response = new StoreItemEvent(itemEvent.getItem(), itemEvent.getWarehouse());

            Warehouse selectedWarehouse = itemEvent.getWarehouse();

            if (selectedWarehouse != null) {
                return new StoreItemEvent(selectedWarehouse.storeItem(itemEvent.getItem()), itemEvent.getWarehouse());
            }
            ArrayList<Warehouse> warehouses = warehouseManager.getWarehouses();
            for (Warehouse warehouse:warehouses) {
                Item stored = warehouse.storeItem(itemEvent.getItem());
                if (stored != null) {
                    StoreItemEvent success = new StoreItemEvent(stored, warehouse);
                    success.setSuccess(true);
                   return success;
                }
            }
            return null;
        }
        return null;
    }
}
