package app.events;

import app.App;
import app.EventStream;
import cli.helper.CliOutput;

import java.io.PrintStream;

public class RegisterEventOutput implements RegisterModuleEvent {
    private PrintStream output;

    public RegisterEventOutput(PrintStream output) {
        this.output = output;
    }

    @Override
    public Module registerModule(App app) {
        return new CliOutput(output);
    }

    @Override
    public void connectToStream(EventStream eventStream) {

    }
}
