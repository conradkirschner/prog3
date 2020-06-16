package famework.event;

import java.util.ArrayList;

public interface Subscriber {
    ArrayList<Event> getSubscribedEvents();
    Event update(Event event);
}
