package app.network.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class NetworkResponseEvent implements Event {
    static String name = NetworkResponseEvent.class.getName();

    private Event response;

    public NetworkResponseEvent(NetworkRequestEvent event) {
        response=event;
    }

    public Event getResponse() {
        return response;
    }

    public NetworkResponseEvent setResponse(Event response) {
        this.response = response;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    private Boolean status;
    private String type;
    private Event event;


    public NetworkResponseEvent(Boolean status) {
        this.status = status;
    }
    public NetworkResponseEvent(String type) {
        this.type = type;
    }

    /**
     * Klick to see subscriber
     */
    @EventRegister
    public NetworkResponseEvent() {
        this.status = false;
    }

    public static void setName(String name) {
        NetworkResponseEvent.name = name;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
