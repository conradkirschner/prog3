package app.user.model;

import app.user.UserManager;
import app.user.events.DeleteUserEvent;
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
public class Delete implements Subscriber {

    private boolean active = true;

    @Inject
    UserManager userManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new DeleteUserEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof DeleteUserEvent){
            String username = ((DeleteUserEvent) event).getUsername();
            return new DeleteUserEvent(userManager.removeUser(username));
        }
        return null;
    }

    @Override
    public String getName() {
        return "user:Delete";
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
