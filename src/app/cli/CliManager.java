package app.cli;

import app.cli.events.GetInputEvent;
import app.cli.screens.MainScreen;
import app.cli.screens.Screen;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;

import java.io.PrintStream;

@Service
public class CliManager {
    ConfigBag configBag;
    PrintStream printStream;

    @Inject
    MainScreen mainScreen;

    Screen currentScreen;

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    EventHandler eventHandler;

    public CliManager(ConfigBag configBag, PrintStream printStream, EventHandler eventHandler) {
        this.configBag = configBag;
        this.printStream = printStream;
        this.eventHandler = eventHandler;
    }

    public void run() {
        printStream.println("works");
        mainScreen.getContent();
        GetInputEvent getInput = (GetInputEvent) this.eventHandler.push(new GetInputEvent(""));
    }
}
