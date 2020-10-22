package app.cli.events;

import famework.event.Event;

public class ToggleActiveStateEvent implements Event {
    static String name = ToggleActiveStateEvent.class.getName();
    
    private boolean changeTo;;

    private String[] modifySubscriber;

    public ToggleActiveStateEvent(boolean active, String[] modifySubscriber) {
        this.changeTo = active;
        this.modifySubscriber = modifySubscriber;
    }

    public ToggleActiveStateEvent() {

    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getModifySubscriber() {
        return modifySubscriber;
    }

    public boolean getChangeTo() {
        return changeTo;
    }
}
