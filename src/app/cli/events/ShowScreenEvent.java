package app.cli.events;

import famework.event.Event;

public class ShowScreenEvent implements Event {
    static String name = ShowScreenEvent.class.getName();

    private String view;

    public ShowScreenEvent(String view ) {
        this.view = view;
    }


    @Override
    public String getName() {
        return name;
    }
}
