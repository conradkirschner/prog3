import app.user.events.CreateUserEvent;
import app.user.events.DeleteUserEvent;
import app.user.events.GetUserEvent;
import famework.Kernel;
import famework.event.EventHandler;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

            Kernel kernel = new Kernel();
        EventHandler eventHandler = kernel.run();

        GetUserEvent userEvent = (GetUserEvent) eventHandler.push(new GetUserEvent(null));

        System.out.println("Current User" + userEvent.getUsers());

        eventHandler.push(new CreateUserEvent("test"));
        userEvent = (GetUserEvent) eventHandler.push(new GetUserEvent(null));
        System.out.println("Current User" + userEvent.getUsers());
        userEvent = (GetUserEvent) eventHandler.push(new GetUserEvent(null));
        eventHandler.push(new DeleteUserEvent("test"));
        System.out.println("Current User" + userEvent.getUsers());

    }
}