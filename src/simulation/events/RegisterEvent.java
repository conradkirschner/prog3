package simulation.events;

import app.App;
import app.EventStream;
import app.events.Connectable;
import simulation.Module;


public class RegisterEvent implements Connectable {

    @Override
    public Module registerModule(App app) {
        System.out.println("Simulation Module registered");
        return new Module(app);

    }

    @Override
    public void connectToStream(EventStream eventStream) {
        ModuleEvent moduleEvent = new ModuleEvent();
        eventStream.dataConnector(moduleEvent);

    }
}
