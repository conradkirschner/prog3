package app.cli;

import app.cli.events.CliStartEvent;
import app.cli.screens.MainScreen;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.Event;
import famework.event.Subscriber;

import java.io.PrintStream;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class CliManager implements Subscriber {
    ConfigBag configBag;
    PrintStream printStream;

    @Inject
    MainScreen mainScreen;

    public CliManager(ConfigBag configBag, PrintStream printStream) {
        this.configBag = configBag;
        this.printStream = printStream;
    }


    @Override
    public ArrayList<Event> getSubscribedEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new CliStartEvent());
        return events;
    }

    @Override
    public Event update(Event event) {
        if (event instanceof CliStartEvent) {
            run();
        }
        return null;
    }
    private void run() {
        printStream.println("works");
        mainScreen.getContent();
    }
}
