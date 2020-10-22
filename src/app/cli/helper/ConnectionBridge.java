package app.cli.helper;

import famework.event.Event;
import famework.event.Subscriber;

public class ConnectionBridge {
    public ConnectionBridge(Event event, Subscriber subscriber) {
        this.event = event;
        this.subscriber = subscriber;
    }

    private Event event;
    private Subscriber subscriber;

    public Event getEvent() {
        return event;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
}
