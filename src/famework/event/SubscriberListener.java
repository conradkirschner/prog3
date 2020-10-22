package famework.event;

public class SubscriberListener implements Listener {
    private Event event;
    private Subscriber subscriber;
    private int prio;

    public SubscriberListener(Event event, Subscriber subscriber, int prio) {
        this.event = event;
        this.subscriber = subscriber;
        this.prio = prio;
    }

    public Event getEvent() {
        return this.event;
    }

    @Override
    public Event update(Event listenerEvent) {
        if(event.getName().equals(listenerEvent.getName())){
           return subscriber.update(listenerEvent);
        }
        return null;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    @Override
    public int getPrio() {
        return this.prio;
    }
}
