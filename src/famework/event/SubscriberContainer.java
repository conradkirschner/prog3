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
        try {
            return (Event) subscribed;
        } catch (ClassCastException e) {
            System.out.println("Did you set the correct Events to the Subscriber?");
        }
        return null;
    }

    @Override
    public int getPrio() {
        return this.prio;
    }
}
