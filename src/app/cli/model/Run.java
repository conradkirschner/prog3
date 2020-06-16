package app.cli.model;


import app.cli.CliManager;
import app.cli.events.CliStartEvent;
import app.warehouse.events.CreateWarehouseEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Run implements Subscriber {

    @Inject
    EventHandler eventHandler;

    @Inject
    CliManager cliManager;

    @Override
    public ArrayList<SubscriberContainerInterface>  getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CliStartEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        CreateWarehouseEvent createWarehouse = (CreateWarehouseEvent) this.eventHandler.push(new CreateWarehouseEvent("own"));

        while (true) {
            cliManager.run();
        }
    }
}
