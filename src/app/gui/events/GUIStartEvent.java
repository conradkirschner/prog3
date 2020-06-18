package app.gui.events;

import famework.event.Event;

public class GUIStartEvent implements Event {
    static String name = GUIStartEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }
}
