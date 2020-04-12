package app.events;

import app.App;
import app.EventStream;

public class RegisterEventStreamEvent implements RegisterModuleEvent {

    @Override
    public Module registerModule(App app) {
        System.out.println("EventStream Registered");

        return new app.Module(app);

    }
}
