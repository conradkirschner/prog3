package app.cli.model;


import app.cli.CliManager;
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
public class CreateUser implements Subscriber {

    boolean active = true;

    @Inject
    CliManager cliManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CreateUserEvent(), 1000));
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof CreateUserEvent) {
            if (((CreateUserEvent) event).getStatus()) {
                cliManager.setFlashMessage(
                                "Kunde: \""
                                +((CreateUserEvent) event).getUsername()
                                +"\" - erfolgreich gespeichert!");
                cliManager.setPreviousScreen();
                return null;
            }
            cliManager.showError(
                    "Kunde: \""
                            +((CreateUserEvent) event).getUsername()
                            +"\" konnte nicht angelegt werden");

        }
        return null;
    }

    @Override
    public String getName() {
        return "cli:CreateUser";
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }
}
