package cli;

import app.App;
import user.model.UserManager;
import cli.events.ModuleEvent;

public class Module implements app.events.Module {
    public Cli cli;
    private App app;

    public Module(App app) {
        this.app = app;
        warehouse.Module warehouseModule = (warehouse.Module) app.getModule("warehouse");
        user.Module userModule = (user.Module) app.getModule("user");
        app.Module appModule = (app.Module) app.getModule("event-stream");

        ModuleEvent moduleEvent2 = new ModuleEvent("Log", "verbose");
        appModule.eventStream.dataConnector(moduleEvent2);
        this.cli = new Cli(warehouseModule.warehouse, userModule.userManager, appModule.eventStream);
    }

    @Override
    public String getName() {
        return "cli";
    }
}
