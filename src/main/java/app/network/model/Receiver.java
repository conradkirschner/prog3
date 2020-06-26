package app.network.model;


import app.network.events.NetworkResponseEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Receiver implements Subscriber {

    @Inject
    EventHandler eventHandler;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new NetworkResponseEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof NetworkResponseEvent){
            NetworkResponseEvent networkResponseEvent = (NetworkResponseEvent) event;
            Event event1 = networkResponseEvent.getEvent();
            if (event1 == null) return null;
            eventHandler.push(event1);
        }
        return null;
    }


}
