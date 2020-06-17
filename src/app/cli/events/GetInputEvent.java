package app.cli.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class GetInputEvent implements Event {
    static String name = GetInputEvent.class.getName();

    private String content;
    private String view;

    public GetInputEvent(String content) {
        this.content = content;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public GetInputEvent() {
        this.content = null;
    }
    public String getContent() {
        return content;
    }

    @Override
    public String getName() {
        return name;
    }
}
