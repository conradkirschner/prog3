package app.cli.model;


import app.cli.events.ToggleActiveStateEvent;
import app.cli.helper.ConnectionBridge;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;
import java.util.List;

@Service
@AutoloadSubscriber
public class ToggleActiveState implements Subscriber {

    private boolean active = true;

    @Inject
    EventHandler eventHandler;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new ToggleActiveStateEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof ToggleActiveStateEvent) {
            String[] modifiySubscriber = ((ToggleActiveStateEvent) event).getModifySubscriber();
            boolean toState = ((ToggleActiveStateEvent) event).getChangeTo();


            ArrayList<Listener> activeListeners = this.eventHandler.getRegistry().getListeners();
            List<ConnectionBridge> activeListenersString = new ArrayList<ConnectionBridge>();

            if (activeListeners != null) {
                for (Listener activeListener : activeListeners) {
                    Subscriber subscribedToCall = activeListener.getSubscriber();
                    if (subscribedToCall == null) {
                        continue;
                    }

                    ArrayList<SubscriberContainerInterface> subscribedTos = subscribedToCall.getSubscribedEvents();
                    if (subscribedTos == null) {
                        continue;
                    }
                    for (SubscriberContainerInterface subscribedTo : subscribedTos) {
                        Event connectedToEvent = (Event) subscribedTo.getSubscribedEvent();
                        if (connectedToEvent == null) {
                            continue;
                        }
                        activeListenersString.add(new ConnectionBridge( connectedToEvent, subscribedToCall));


                    }
                }
            }
            for (ConnectionBridge eventConnection: activeListenersString) {
                Subscriber subscriber = eventConnection.getSubscriber();
                Event eventName = eventConnection.getEvent();

                for (String subscriberName: modifiySubscriber) {
                    if (subscriber.getName().equals(subscriberName)) {
                        subscriber.setActive(toState);
                    }
                }

                System.out.println("Subscriber " + subscriber.getName());
                System.out.println("Läuft    ---->  " + String.valueOf(subscriber.isActive()));
                System.out.println("Event    ---->   " + event.getName());
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
