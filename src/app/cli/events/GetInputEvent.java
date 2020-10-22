package app.cli.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class GetInputEvent implements Event {
    static String name = GetInputEvent.class.getName();

    private String content;
    private boolean validated;

    public GetInputEvent(String content) {
        this.content = content;
        validated = false;
    }

    public GetInputEvent(String content, boolean validated) {
        this.content = content;
        this.validated = validated;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public GetInputEvent() {
        this.content = null;
        this.validated = false;
    }
    public String getContent() {
        return content;
    }

    public boolean isValidated() {
        return validated;
    }

    @Override
    public String getName() {
        return name;
    }
}
