package app;

import app.cli.events.CliStartEvent;
import app.persistence.events.LoadApplicationEvent;
import app.user.events.GetUserEvent;
import app.warehouse.events.CreateStoragePlaceEvent;
import app.warehouse.events.CreateWarehouseEvent;
import famework.Kernel;
import famework.event.EventHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main extends Application  {

    public void start(Stage stage) throws Exception {
        Parameters parameters = getParameters();
        Integer storageCount = null;
        try {
            storageCount = new Integer(parseInt(parameters.getUnnamed().get(0)));
        } catch (final NumberFormatException | IndexOutOfBoundsException e ) {}

        Kernel kernel = new Kernel();
        ArrayList<Object> config = new ArrayList<>();
        config.add(System.out);
        config.add(System.in);
        EventHandler eventHandler = kernel.run(config);
//        eventHandler.push(new ServerStartEvent());

        eventHandler.push(new LoadApplicationEvent("JOS"));
        eventHandler.push(new CreateWarehouseEvent("own"));
//        eventHandler.push(new GUIStartEvent(eventHandler));
        if (storageCount != null) {
            eventHandler.push(new CreateStoragePlaceEvent(storageCount,1000, "own"));
        }
       Platform.runLater(new Runnable(){
            @Override
            public void run() {

            }
        });

//       Platform.runLater(new Runnable(){
//            @Override
//            public void run() {
//                eventHandler.push(new CliStartEvent());
//
//
//            }
//        });

        new Thread(() -> eventHandler.push(new CliStartEvent())).start();
        GetUserEvent userEvent = (GetUserEvent) eventHandler.push(new GetUserEvent());

//
//        System.out.println("Current Warehouse" + userEvent.getWarehouses());
//
//        eventHandler.push(new CreateWarehouseEvent("test"));
//        userEvent = (GetWarehouseEvent) eventHandler.push(new GetWarehouseEvent(null));
//        System.out.println("Current Warehouse" + userEvent.getWarehouses());
//        userEvent = (GetWarehouseEvent) eventHandler.push(new GetWarehouseEvent(null));
//        eventHandler.push(new DeleteUserEvent("test"));
//        System.out.println("Current Warehouse" + userEvent.getWarehouses());

    }
}