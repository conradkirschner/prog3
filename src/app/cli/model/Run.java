package app.cli.model;


import app.cli.CliManager;
import app.cli.events.CliStartEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class Run implements Subscriber {

    @Inject
    CliManager cliManager;

    @Override
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new CliStartEvent());
        return events;
    }

    @Override
    public Event update(Event event) {
        cliManager.run();
        return null;
    }
}
