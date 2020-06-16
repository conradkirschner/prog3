package app.cli.model;


import app.cli.CliManager;
import app.cli.events.CliStartEvent;
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
public class Run implements Subscriber {

    @Inject
    CliManager cliManager;

    @Override
    public ArrayList<SubscriberContainerInterface>  getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new CliStartEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        while (true) {
            cliManager.run();
        }
    }
}
