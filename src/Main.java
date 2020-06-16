import app.cli.events.CliStartEvent;
import app.user.events.GetUserEvent;
import famework.Kernel;
import famework.event.EventHandler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

            Kernel kernel = new Kernel();
        ArrayList<Object> config = new ArrayList<>();
        config.add(System.out);
        config.add(System.in);
        EventHandler eventHandler = kernel.run(config);
       eventHandler.push(new CliStartEvent());
        GetUserEvent userEvent = (GetUserEvent) eventHandler.push(new GetUserEvent(null));
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