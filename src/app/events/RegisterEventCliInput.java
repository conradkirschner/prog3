package app.events;

import app.App;
import app.EventStream;
import cli.helper.CliInput;

import java.io.BufferedReader;

public class RegisterEventCliInput implements RegisterModuleEvent {
    private BufferedReader input;

    public RegisterEventCliInput(BufferedReader input) {
        this.input = input;
    }

    @Override
    public Module registerModule(App app) {
        return new CliInput(input);
    }

    @Override
    public void connectToStream(EventStream eventStream) {

    }
}
