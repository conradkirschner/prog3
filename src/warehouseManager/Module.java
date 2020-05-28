package warehouseManager;

import warehouseManager.model.WarehouseManager;
import app.App;

public class Module implements app.events.Module {
    public WarehouseManager warehouseManager;
    private App app;

    public Module(App app) {
        this.app = app;
        app.Module appModule = (app.Module) app.getModule("event-stream");

        this.warehouseManager = new WarehouseManager(appModule.eventStream);
    }

    @Override
    public String getName() {
        return "warehouse-manager";
    }

    public WarehouseManager getModule() {
        return this.warehouseManager;
    }
}
