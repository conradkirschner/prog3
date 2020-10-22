package app.cli.events;

import famework.event.Event;

public class ShowScreenEvent implements Event {
    static String name = ShowScreenEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
}
