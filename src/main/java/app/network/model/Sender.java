package app.network.model;


import app.network.NetworkManager;
import app.network.events.NetworkRequestEvent;
import app.network.events.NetworkResponseEvent;
import famework.annotation.AutoloadListener;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.io.PrintStream;

@Service
@AutoloadListener
public class Sender implements famework.event.Listener {
    PrintStream out;

    @Inject
    NetworkManager networkManager;

    public Sender(PrintStream printStream) {
        this.out = printStream;
    }

    @Override
    public Event update(Event event) {

        this.out.println(event.getName());
       if(event instanceof NetworkRequestEvent || event instanceof NetworkResponseEvent) return null;
        networkManager.push(new NetworkRequestEvent(event));

        return null;
    }

    @Override
    public int getPrio() {
        return 0;
    }

    @Override
    public Subscriber getSubscriber() {
        return null;
    }
}