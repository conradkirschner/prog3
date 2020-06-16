package app.example;

import famework.annotation.AutoloadSubscriber;
import famework.event.Event;

import java.util.ArrayList;

@AutoloadSubscriber
public class Subscriber implements famework.event.Subscriber {
    @Override
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new TestEvent());
        return events;
    }

    @Override
    public Event update(Event event) {
        return null;
    }
}
