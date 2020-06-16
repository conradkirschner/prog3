package app.cli.events;

import famework.event.Event;

public class CliInputEvent implements Event {
    static String name = CliInputEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
}
