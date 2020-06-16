package app.user.model;

import app.user.UserManager;
import app.user.events.DeleteUserEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Delete implements Subscriber {

    @Inject
    UserManager userManager;

    @Override
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new DeleteUserEvent(""));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof DeleteUserEvent){
            String username = ((DeleteUserEvent) event).getUsername();
            userManager.removeUser(username);
            return null;
        }
        return null;
    }
}
