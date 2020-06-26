package app.network.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class NetworkRequestEvent implements Event {
    static String name = NetworkRequestEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }

    private Boolean status;
    private String type;
    private Event event;

    public NetworkRequestEvent(Event event) {
        this.event = event;
    }

    public NetworkRequestEvent(Boolean status) {
        this.status = status;
    }
    public NetworkRequestEvent(String type) {
        this.type = type;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public NetworkRequestEvent() {
        this.status = false;
    }

    public Boolean getStatus() {
        return status;
    }

    public static void setName(String name) {
        NetworkRequestEvent.name = name;
    }

    public NetworkRequestEvent setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public NetworkRequestEvent setType(String type) {
        this.type = type;
        return this;
    }

    public Event getEvent() {
        return event;
    }

    public NetworkRequestEvent setEvent(Event event) {
        this.event = event;
        return this;
    }

    public String getType() {
        return type;
    }
}
