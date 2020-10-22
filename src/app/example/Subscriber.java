package app.example;

import famework.annotation.AutoloadSubscriber;
import famework.event.Event;
import famework.event.SubscriberContainer;
import famework.event.SubscriberContainerInterface;

import java.util.ArrayList;

@AutoloadSubscriber
public class Subscriber implements famework.event.Subscriber {
    private boolean active = true;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new TestEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        return null;
    }

    @Override
    public String getName() {
        return "example:Subscriber";
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
