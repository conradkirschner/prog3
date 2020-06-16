package famework.event;

public class EventHandler {
    private EventRegistry registry;

    public EventHandler() {
        this.registry = new EventRegistry();
    }
    public void registerListener(Listener listener){
        this.registry.addListener(listener);
    }
    public void registerSubscriber(Subscriber subscriber) {
        if (subscriber == null) return;
        if(subscriber.getSubscribedEvents() == null) {
            System.out.println("noEvents found");
        }
        for(Event event: subscriber.getSubscribedEvents()) {

            Listener listener = new SubscriberListener(event, subscriber);
            this.registry.addListener(listener);
        }

    }

    public Event push(Event event) {
        return this.registry.push(event);
    }
}
