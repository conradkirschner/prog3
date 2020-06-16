package app.cli.events;

import famework.event.Event;

public class GetInputEvent implements Event {
    static String name = GetInputEvent.class.getName();

    private String content;

    public GetInputEvent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    @Override
    public String getName() {
        return name;
    }
}
