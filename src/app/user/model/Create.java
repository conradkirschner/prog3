package app.user.model;


import app.user.UserManager;
import app.user.entity.User;
import app.user.events.CreateUserEvent;
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
public class Create implements Subscriber {

    private boolean active = true;

    @Inject
    UserManager userManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CreateUserEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof CreateUserEvent){
            User user = new User(((CreateUserEvent) event).getUsername());
            userManager.newUser(user);
            System.out.println("Added new user");
            System.out.println(user.getUsername());
            return new CreateUserEvent(user.getUsername(), true);
        }
        return null;
    }

    @Override
    public String getName() {
        return "user:Create";
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
