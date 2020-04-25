package warehouse;

import app.App;
import warehouse.model.Warehouse;

public class Module implements app.events.Module {
    public Warehouse warehouse;
    private App app;

    public Module(App app) {
        this.app = app;
        user.Module userModule = (user.Module) app.getModule("user");
        app.Module appModule = (app.Module) app.getModule("event-stream");

        this.warehouse = new Warehouse("test", appModule.eventStream);
    }

    @Override
    public String getName() {
        return "warehouse";
    }

    public Warehouse getModule() {
        return this.warehouse;
    }
}
