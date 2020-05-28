package simulation;

import app.App;
import user.model.UserManager;

public class Module implements app.events.Module {
    public UserManager userManager;
    private App app;

    public Module(App app) {
        this.app = app;
        this.userManager = new UserManager();
    }

    @Override
    public String getName() {
        return "user";
    }
}