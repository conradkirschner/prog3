package app.network;

import app.network.events.NetworkRequestEvent;
import app.network.helper.Tcp;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;

import java.io.IOException;

@Service
public class NetworkManager {
    private boolean isUDP;

    public NetworkManager(ConfigBag configBag) {
        isUDP = configBag.props.getProperty("protocol").equals("udp");
    }

    @Inject
    private EventHandler eventHandler;
    @Inject
    private Server server;

    @Inject
    private Tcp tcp;

    private boolean isConnected;

    public void runServer(){
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean push (NetworkRequestEvent event) {
        if(!isConnected) {

            new Thread(() -> {
                this.tcp.connect();
                this.tcp.push(event);

            }).start();
            isConnected = true;
        } else {
            this.tcp.push(event);
        }
        return true;
    }
}
