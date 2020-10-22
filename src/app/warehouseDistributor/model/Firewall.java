package app.warehouseDistributor.model;


import app.warehouse.entity.Item;
import app.warehouse.events.*;
import app.warehouseDistributor.events.ActivateWarehouseEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;
import java.util.UUID;

@Service
@AutoloadSubscriber
public class Firewall implements Subscriber {

    private boolean active = true;

    @Inject
    EventHandler eventHandler;

    ArrayList<String> activeWarehouses = new ArrayList<>();

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new ActivateWarehouseEvent(null), 0));
        events.add(new SubscriberContainer(new CreateWarehouseEvent(), -100));
        events.add(new SubscriberContainer(new StoreItemEvent(), -100));
        events.add(new SubscriberContainer(new GetItemEvent(), 100));
        events.add(new SubscriberContainer(new UpdateItemEvent(), -100));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof ActivateWarehouseEvent) {
            this.activeWarehouses = ((ActivateWarehouseEvent) event).getWarehouseIds();
            return null;
        }
        if (event instanceof CreateWarehouseEvent) {
            if (!this.activeWarehouses.contains(((CreateWarehouseEvent) event).getId())) {
                this.activeWarehouses.add(((CreateWarehouseEvent) event).getId());

            }
            return null;
        }

        Event returnEvent = event;
        if (event instanceof GetItemEvent) {
            System.out.println("Distrbutor Action - GetItem Event" );
            ArrayList<Item> items = ((GetItemEvent) event).getItems();
            ArrayList<Item> clean = new ArrayList<>();
            if (items == null) {
                return event;
            }
            for(String warehouse:activeWarehouses) {
                for(Item item:items) {
                    if(item.getWarehouse().equals(warehouse)) {
                        clean.add(item);
                    }
                }
            }
            return new GetItemEvent(clean);

        } else if (event instanceof StoreItemEvent) {
            if (((StoreItemEvent) event).getWarehouse() != null) {
                return event;
            }

            for (String warehouse : activeWarehouses) {
                GetWarehouseEvent selectedWarehouse = (GetWarehouseEvent) eventHandler.push(new GetWarehouseEvent(warehouse));
                System.out.println("Share Storage to Warehouse - " + selectedWarehouse.getWarehouse().getId());
                UUID uuid = UUID.randomUUID();
                ((StoreItemEvent) event).getItem().setId(uuid.toString());
                StoreItemEvent storeItemEvent = (StoreItemEvent) eventHandler.push(new StoreItemEvent(
                        ((StoreItemEvent) event).getItem(),
                        selectedWarehouse.getWarehouse()
                ));
            }

        } else if (event instanceof UpdateItemEvent) {
            System.out.println("Distrbutor Action - UpdateItem Event" );
        } else {
            System.out.println("Distrbutor Action - wrong event" );
        }
        return returnEvent;
    }

    @Override
    public String getName() {
        return "warehouseDistributor:Firewall";
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
