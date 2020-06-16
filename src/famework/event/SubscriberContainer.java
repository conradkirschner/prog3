package famework.event;

public class SubscriberContainer implements SubscriberContainerInterface {
    private Object subscribed;
    private int prio;

    public SubscriberContainer(Object subscribed, int prio) {
        this.subscribed = subscribed;
        this.prio = prio;
    }

    @Override
    public Event getSubscribedEvent() {
        return (Event) subscribed;
    }

    @Override
    public int getPrio() {
        return this.prio;
    }
}
