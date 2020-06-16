package app.cli.events;

import app.example.TestEvent;
import famework.event.Event;

public class CliStartEvent implements Event {
    static String name = TestEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
}
