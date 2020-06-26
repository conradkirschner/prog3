package app.network.events;

import famework.annotation.EventRegister;
import famework.event.Event;

public class ServerStartEvent implements Event {
    static String name = ServerStartEvent.class.getName();

    @Override
    public String getName() {
        return name;
    }


    /**
     * Klick to see subscriber
     */
    @EventRegister
    public ServerStartEvent() {
    }
}
