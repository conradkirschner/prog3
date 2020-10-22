package app.gui.model;


import app.cli.CliManager;
import app.gui.Window;
import app.gui.events.GUIStartEvent;
import famework.annotation.AutoloadSubscriber;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

@Service
@AutoloadSubscriber
public class RunGui implements Subscriber {

    private boolean active = true;

    @Inject
    EventHandler eventHandler;

    @Inject
    CliManager cliManager;

    @Inject
    Window window;

    PrintStream printStream;
    BufferedInputStream in;

    public RunGui(BufferedInputStream in, PrintStream printStream) {
        this.in = in;
        this.printStream = printStream;
    }


    @Override
    public ArrayList<SubscriberContainerInterface> getSubscribedEvents() {
        ArrayList<SubscriberContainerInterface> events = new ArrayList<>();
        events.add(new SubscriberContainer(new GUIStartEvent(), 0));
        return events;
    }

    @Override
    public Event update(Event event) {
        try {
            window.start( ((GUIStartEvent)event).getEventHandler());
        } catch (IOException e) {
           return null;
        }

        return null;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    @Override
    public String getName() {
        return "gui:RunGui";
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
