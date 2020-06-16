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
//            System.out.println("noEvents found");
        }
        for(SubscriberContainerInterface subscriberContainerInterfaces: subscriber.getSubscribedEvents()) {
            Event event = (Event) subscriberContainerInterfaces.getSubscribedEvent();
            Listener listener = new SubscriberListener(event, subscriber, subscriberContainerInterfaces.getPrio());
            this.registry.addListener(listener);
        }

    }

    public Event push(Event event) {
        return this.registry.push(event);
    }
}
