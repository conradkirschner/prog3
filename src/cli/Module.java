package cli;

import app.App;
import cli.events.ModuleEvent;
import cli.helper.CliInput;
import cli.helper.CliOutput;

public class Module implements app.events.Module {
    public Cli cli;
    private App app;

    public Module(App app) {
        this.app = app;
        app.Module appModule = (app.Module) app.getModule("event-stream");
        CliInput cliInput = (CliInput) app.getModule("cli-input");
        CliOutput cliOutput = (CliOutput) app.getModule("cli-output");

        this.cli = new Cli(
                appModule.eventStream,
                cliInput.input,
                cliOutput.output
        );
    }

    public Cli getModule() {
        return this.cli;
    }

    @Override
    public String getName() {
        return "cli";
    }
}
