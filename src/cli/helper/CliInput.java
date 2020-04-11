package cli.helper;

import app.events.Module;

import java.io.BufferedReader;

public class CliInput implements Module {
    public BufferedReader input;

    public CliInput(BufferedReader input) {
        this.input = input;
    }

    @Override
    public String getName() {
        return "cli-input";
    }
}
