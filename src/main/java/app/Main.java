package app;

import app.gui.events.GUIStartEvent;
import app.user.events.GetUserEvent;
import famework.Kernel;
import famework.event.EventHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main  extends Application {

    public void start(Stage stage) throws Exception {
        Kernel kernel = new Kernel();
        ArrayList<Object> config = new ArrayList<>();
        config.add(System.out);
        config.add(System.in);
        EventHandler eventHandler = kernel.run(config);
//        eventHandler.push(new LoadApplicationEvent("JOS"));

        eventHandler.push(new GUIStartEvent());
//        Platform.runLater(new Runnable(){
//            @Override
//            public void run() {
//                eventHandler.push(new GUIStartEvent());
//            }
//        });
//       Platform.runLater(new Runnable(){
//            @Override
//            public void run() {
//            }
//        });

//        new Thread(() -> eventHandler.push(new CliStartEvent())).start();
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