package gui.events;

import app.App;
import app.EventStream;
import app.events.Connectable;
import gui.Module;

public class RegisterEvent implements Connectable {

    @Override
    public Module registerModule(App app) {
        System.out.println("GUI Module registered");
        app.Module appModule = (app.Module) app.getModule("event-stream");


        return new Module(app);

    }

    @Override
    public void connectToStream(EventStream eventStream) {
        ModuleEvent moduleEvent = new ModuleEvent();
        eventStream.dataConnector(moduleEvent);

    }
}