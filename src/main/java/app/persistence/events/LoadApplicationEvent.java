package app.persistence.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class LoadApplicationEvent implements Event {
    static String name = LoadApplicationEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }

    private Boolean status;
    private String type;

    public LoadApplicationEvent(Boolean status) {
        this.status = status;
    }
    public LoadApplicationEvent(String type) {
        this.type = type;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public LoadApplicationEvent() {
        this.status = false;
    }

    public Boolean getStatus() {
        return status;
    }


    public String getType() {
        return type;
    }
}
