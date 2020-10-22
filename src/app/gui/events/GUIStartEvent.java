package app.gui.events;

import famework.event.Event;
import famework.event.EventHandler;

public class GUIStartEvent implements Event {
    static String name = GUIStartEvent.class.getName();

    private EventHandler eventHandler;

    public GUIStartEvent() {
    }

    public GUIStartEvent(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    @Override
    public String getName() {
        return name;
    }
}
