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

    private boolean active = true;
    private EventHandler eventHandler;

    public CliManager(ConfigBag configBag, PrintStream printStream, EventHandler eventHandler) {
        this.configBag = configBag;
        this.printStream = printStream;
        this.eventHandler = eventHandler;
        this.history = new ArrayList<>();
        this.active = true;
    }
    public Screen getCurrentScreen() {
        ArrayList<Screen> screens = new ArrayList<>();
        for(Screen screen: this.history) {
            if (screen != null) {
                screens.add(screen);
            }
        }
        int index = screens.size();
        if (index == 0) {
            return mainScreen;
        }
        return history.get(index - 1);
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
            ArrayList<Screen> historyNew = new ArrayList<>();

            for (Screen historyScreen:this.history) {
                if (historyScreen != null) {
                    historyNew.add(historyScreen);
                }
            }
            this.history = historyNew;
            this.setCurrentScreen(this.history.get(0));
        } catch (IndexOutOfBoundsException  e) {
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

    public void stop() {
        this.active = false;
    }

    public void start() {
        this.active = true;
    }

    public boolean shouldRun() {
        return this.active;
    }
}
