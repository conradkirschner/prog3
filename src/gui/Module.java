package gui;

import app.App;

public class Module implements app.events.Module {
    public Window window;
    private App app;

    public Module(App app) {
        this.app = app;
        this.window = new Window();
    }

    @Override
    public String getName() {
        return "gui";
    }
    public Window getModule() {
        return this.window;
    }

}
