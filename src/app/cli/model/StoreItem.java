package app.cli.model;


import app.cli.CliManager;
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

    private boolean active = true;

    @Inject
    CliManager cliManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new StoreItemEvent(), 1000));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof StoreItemEvent) {
            if (((StoreItemEvent) event).getWarehouse() != null) {
                if (((StoreItemEvent) event).getSuccess()) {
                    cliManager.setFlashMessage("Item gespeichert!");
                    cliManager.setPreviousScreen();
                    return null;
                }
                cliManager.showError("Konnte Item nicht speichern");
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "cli:StoreItem";
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
