package famework.event;

import java.util.ArrayList;

public interface Subscriber {
    ArrayList<SubscriberContainerInterface> getSubscribedEvents();
    Event update(Event event);
    public String name = null;
    String getName();
    void setActive(boolean active);
    boolean isActive();
}
