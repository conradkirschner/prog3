package app.cli.events;

import famework.event.Event;

public class ParseInputEvent implements Event {
    static String name = ParseInputEvent.class.getName();

    private String input;

    public ParseInputEvent(String input ) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    @Override
    public String getName() {
        return name;
    }
}
