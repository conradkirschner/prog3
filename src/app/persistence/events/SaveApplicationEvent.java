package app.persistence.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class SaveApplicationEvent implements Event {
    static String name = SaveApplicationEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }

    Boolean status;
    String type;

    public SaveApplicationEvent(Boolean status) {
        this.status = status;
    }
    public SaveApplicationEvent(String type) {
        this.type = type;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public SaveApplicationEvent() {
        this.status = false;
    }

    public Boolean getStatus() {
        return status;
    }


    public String getType() {
        return type;
    }
}
