package app.cli;

import app.cli.screens.MainScreen;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.io.PrintStream;

@Service
public class CliManager {
    ConfigBag configBag;
    PrintStream printStream;

    @Inject
    MainScreen mainScreen;

    public CliManager(ConfigBag configBag, PrintStream printStream) {
        this.configBag = configBag;
        this.printStream = printStream;
        printStream.println("works");
    }

    public int run() {
        mainScreen.getContent();
        return 0;

    }
}
