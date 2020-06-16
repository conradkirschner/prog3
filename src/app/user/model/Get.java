package app.user.model;


import app.user.UserManager;
import app.user.events.GetUserEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Get implements Subscriber {

    @Inject
    UserManager userManager;

    @Override
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new GetUserEvent(null));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof GetUserEvent){

            return new GetUserEvent(userManager.getUser());
        }
        return null;
    }
}
