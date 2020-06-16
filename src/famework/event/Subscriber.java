package famework.event;

import java.util.ArrayList;

public interface Subscriber {
    ArrayList<SubscriberContainerInterface> getSubscribedEvents();
    Event update(Event event);
}
