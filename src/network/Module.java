package network;

import app.App;
import network.model.NetworkManager;

import java.io.IOException;

public class Module implements app.events.Module {
    private App app;
    private NetworkManager networkManager;

    public Module(App app, NetworkManager networkManager) {
        this.app = app;
        this.networkManager = networkManager;
    }

    @Override
    public String getName() {
        return "network";
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void push(String message) throws IOException {
        this.networkManager.connect();
        this.networkManager.push(message);
    }
}
