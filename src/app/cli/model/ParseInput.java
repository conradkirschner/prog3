package app.cli.model;


import app.cli.CliManager;
import app.cli.events.GetInputEvent;
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
public class ParseInput implements Subscriber {

    private String currentView;
    @Inject
    CliManager cliManager;

    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GetInputEvent(""), 100));
        return events;
    }

    @Override
    public Event update(Event event) {
       GetInputEvent getInputEvent = (GetInputEvent) event;
       String input = getInputEvent.getContent();
        if (input.length() > 0 && input.charAt(0) == ':') {
            switch (input.charAt(1)) {
                case 'c':
                    System.out.println("Works___________ASS");
//                    insertMode();
                    break;
                case 'd':
//                    deleteMode();
                    break;
                case 'r':
//                    overviewMode();
                    break;
                case 'u':
//                    updateMode();
                    break;
                case 'p':
//                    persistMode();
                    break;
                case 'x':
//                    this.running = false;
                    break;
                default:
//                    this.output.println("Command not found!");
            }
        }
        return null;
    }
}
