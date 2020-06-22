package app.example;

import famework.annotation.AutoloadListener;
import famework.annotation.Service;
import famework.event.Event;
import famework.event.Subscriber;

import java.io.PrintStream;

@Service
@AutoloadListener
public class Listener implements famework.event.Listener {
    PrintStream out;

    public Listener(PrintStream printStream) {
        this.out = printStream;
    }

    @Override
    public Event update(Event event) {
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
