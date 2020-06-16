package app.user.model;


import app.user.UserManager;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Create implements Subscriber {

    @Inject
    UserManager userManager;

    @Override
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new CreateUserEvent(""));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof CreateUserEvent){
            User user = new User(((CreateUserEvent) event).getUsername());
            userManager.newUser(user);
            return null;
        }
        return null;
    }
}
