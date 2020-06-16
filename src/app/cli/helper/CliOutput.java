package app.cli.helper;


import java.io.PrintStream;

public class CliOutput  {
    public PrintStream output;

    public CliOutput(PrintStream output) {
        this.output = output;
    }

    public PrintStream getModule() {
        return this.output;
    }

    public String getName() {
        return "cli-output";
    }
}
