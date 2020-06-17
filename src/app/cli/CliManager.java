package app.cli;

import app.cli.events.GetInputEvent;
import app.cli.screens.MainScreen;
import app.cli.screens.Screen;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;

import java.io.PrintStream;
import java.util.ArrayList;

@Service
public class CliManager {
    private ConfigBag configBag;
    private PrintStream printStream;

    @Inject
    private MainScreen mainScreen;

    private ArrayList<Screen> history;
    private Screen currentScreen;
    private String flashMessage;

    private EventHandler eventHandler;

    public CliManager(ConfigBag configBag, PrintStream printStream, EventHandler eventHandler) {
        this.configBag = configBag;
        this.printStream = printStream;
        this.eventHandler = eventHandler;
        this.history = new ArrayList<>();
    }
    public Screen getCurrentScreen() {
        int index = history.size() -1;
        if (index < 0) {
            return mainScreen;
        }
        return history.get(index);
    }

    public void setCurrentScreen(Screen newScreen) {
        this.history.add(newScreen);
        this.currentScreen = newScreen;
    }


    public void run() {
        currentScreen = this.getCurrentScreen();
        this.showFlashMassage();
        currentScreen.getContent();
        currentScreen.getUsage();
        GetInputEvent getInput = (GetInputEvent) this.eventHandler.push(new GetInputEvent());
    }

    public void setPreviousScreen() {
        try {
            int index = this.history.size() - 1;
            this.history.remove(index);
            this.setCurrentScreen(this.history.get(-1));
        } catch (ArrayIndexOutOfBoundsException e) {
            this.setCurrentScreen(mainScreen);
        }
    }

    public void showError(String string) {
        printStream.println("\u001B[31m" + string + "\u001B[30m");
    }
    public void showFlashMassage() {
        if (flashMessage != null) {
            printStream.println("\u001B[32m" + flashMessage + "\u001B[30m");
        }
        flashMessage = null;
    }
    public void setFlashMessage(String flashMessage) {
        this.flashMessage = flashMessage;
    }
}
