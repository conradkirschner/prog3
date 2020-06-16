package app.example;

import famework.annotation.AutoloadListener;
import famework.event.Event;

@AutoloadListener
public class Listener implements famework.event.Listener {
    @Override
    public Event update(Event event) {
        System.out.println("Triggered");
        return null;
    }

    @Override
    public int getPrio() {
        return 0;
    }
}
