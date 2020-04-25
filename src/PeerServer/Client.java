package PeerServer;

import java.util.UUID;

public class Client {
    private String ip;
    private int port;
    private String uuid;

    public Client(String ip, int port, String uuid) {
        this.ip = ip;
        this.port = port;
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return this.ip + ":" + this.port + "§§" + this.uuid;
    }
}
