package famework.event;

public class SubscriberListener implements  Listener {
    private Event event;
    private Subscriber subscriber;

    public SubscriberListener(Event event, Subscriber subscriber) {
        this.event = event;
        this.subscriber = subscriber;
    }

    @Override
    public Event update(Event listenerEvent) {
        if(event.getName().equals(listenerEvent.getName())){
           return subscriber.update(listenerEvent);
        }
        return null;
    }

    @Override
    public int getPrio() {
        return 0;
    }
}
