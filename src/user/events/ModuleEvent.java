package user.events;

import app.App;
import app.events.Event;

import java.io.IOException;
import java.text.ParseException;

public class ModuleEvent implements app.events.ModuleEvent {

    public Boolean shouldRun;
    public Boolean shouldReturn;


    public ModuleEvent() {
        this.shouldRun = true;
        this.shouldReturn = false;
    }
    public void stopRun() {
        this.shouldRun = false;
    }

    @Override
    public String getName() {
        return "user";
    }

    @Override
    public Boolean shouldRun() {
        return shouldRun;
    }

    @Override
    public void returnHere() {
        this.shouldReturn = true;
    }

    @Override
    public void returnStop() {
        this.shouldReturn = false;
    }

    @Override
    public Boolean shouldReturn() {
        if (this.shouldReturn) {
            this.shouldReturn = false;
            return true;
        }
        return false;
    }

    public Event runModuleEvent(String command, String data, App app, Event event) throws IOException, ParseException {
        user.Module userModule = (user.Module) app.getModule("user");
        switch (command) {
            case "user:get":
                this.returnHere();
                return userModule.userManager.getCustomer(data);
            case "user:getAll":
                this.returnHere();
                return new UserListData(userModule.userManager.getCustomers());
            case "user:new":
                this.returnHere();
                return userModule.userManager.newCustomer(data);
            case "user:update":
                this.returnHere();
                String[] split = data.split(":");
                return userModule.userManager.updateCustomer(Integer.parseInt(split[0]), split[1]);
            case "user:delete":
                userModule.userManager.deleteCustomer(data);
                break;
        }

        System.out.print("user triggered - ");
        //this.stopRun();
        return null;
    }
}
