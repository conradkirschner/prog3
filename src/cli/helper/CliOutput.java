package cli.helper;

import app.events.Module;

import java.io.BufferedReader;
import java.io.PrintStream;

public class CliOutput implements Module {
    public PrintStream output;

    public CliOutput(PrintStream output) {
        this.output = output;
    }

    public PrintStream getModule() {
        return this.output;
    }

    @Override
    public String getName() {
        return "cli-output";
    }
}
