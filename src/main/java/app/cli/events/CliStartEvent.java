package app.cli.events;

import famework.event.Event;

public class CliStartEvent implements Event {
    static String name = CliStartEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
}
