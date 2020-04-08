package cli.events;

import app.App;
import app.EventStream;
import app.events.RegisterModuleEvent;
import cli.Module;

public class RegisterEvent implements RegisterModuleEvent {

    @Override
    public Module registerModule(App app) {
        System.out.println("CLI Registered");

        return new Module(app);

    }

    @Override
    public void connectToStream(EventStream eventStream) {

    }

}
