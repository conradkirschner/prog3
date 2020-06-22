package famework.event;

import java.util.ArrayList;

public class EventHandler {
    private EventRegistry registry;
    private ArrayList<Event> history;


    public EventHandler() {
        this.registry = new EventRegistry();
        this.history = new ArrayList<>();
    }
    public void registerListener(Listener listener){
        this.registry.addListener(listener);
    }
    public void registerSubscriber(Subscriber subscriber) {
        if (subscriber == null) return;
        if(subscriber.getSubscribedEvents() == null) {
//            System.out.println("noEvents found");
        }
        for(SubscriberContainerInterface subscriberContainerInterfaces: subscriber.getSubscribedEvents()) {
            Event event = (Event) subscriberContainerInterfaces.getSubscribedEvent();
            Listener listener = new SubscriberListener(event, subscriber, subscriberContainerInterfaces.getPrio());
            this.registry.addListener(listener);
        }

    }

    public Event push(Event event) {
        history.add(event);
        return this.registry.push(event);
    }

    public EventRegistry getRegistry() {
        return registry;
    }

    public ArrayList<Event> getHistory() {
        return this.history;
    }
}
