package app.user.model;


import app.user.UserManager;
import app.user.events.GetUserEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;
import famework.event.SubscriberContainer;
import famework.event.SubscriberContainerInterface;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Get implements Subscriber {

    @Inject
    UserManager userManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetUserEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof GetUserEvent) {
            if (((GetUserEvent) event).getFilterByName().equals("")) {
                return new GetUserEvent(userManager.getUser());
            }
            return new GetUserEvent(userManager.getUser(((GetUserEvent) event).getFilterByName()));

        }
        return null;
    }
}
