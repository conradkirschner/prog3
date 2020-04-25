package network.events;

import PeerServer.Client;
import app.App;
import app.events.Event;
import cli.Cli;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

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
        network.Module networkModule = (network.Module) app.getModule("network");
        ArrayList<Client> clients =new ArrayList<Client>();
        System.out.println("New network Request internal" + data);
        switch (command) {
            case "network:connect":
                String[] clientList = data.split("/");
                for(String clientRow: clientList) {
                    if (clientRow.equals("")) continue;
                    String[] clientData = clientRow.split(":");
                    if (clientData[1].equals("")) continue;

                    String port = clientData[1].split("§§")[0];
                    String uuid = clientData[1].split("§§")[1];
                    if (clientData[0].equals("")) continue;
                    if (port.equals("")) continue;
                    if (uuid.equals("")) continue;
                    clients.add(new Client(clientData[0], Integer.parseInt(port.trim()), uuid));
                }
                networkModule.getNetworkManager().setClients(clients);
                System.out.println("new clients adding " + data);
            break;
            default:
                networkModule.push(command + "##" + data);

                System.out.print("network triggered - ");
        }
        //this.stopRun();
        return null;
    }
}
