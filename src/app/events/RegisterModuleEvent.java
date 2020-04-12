package app.events;

import app.App;
import app.EventStream;
import app.events.Module;

public interface RegisterModuleEvent {

    public Module registerModule(App app);
}
