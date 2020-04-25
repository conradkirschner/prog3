package network.model;

import PeerServer.Client;
import app.EventStream;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class NetworkManager {
    private ArrayList<Client> clients;
    private Server server;
    private DatagramSocket socket = null;

    private boolean alive = false;
    public NetworkManager(EventStream eventStream) {
        this.clients = new ArrayList<Client>();
        this.server = new Server();
        this.server.setEventStream(eventStream);
    }


    public void connect() throws IOException {
        System.out.println("Connecting ... "+ this.clients.size());
        if (this.socket == null) {
            this.socket = new DatagramSocket(new Random().nextInt(2121) + 110);
            this.server.setSocket(this.socket);
            this.server.start();
            hearhbead();
        }
    }

    public void hearhbead() {
        Runnable runnable = () -> {
            try {
                sleep(10000);
                push("alive");
                alive = true;
                hearhbead();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
                alive = false;
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    public void push(String message) throws IOException {
        System.out.println(clients.toString());
        for(Client client: clients) {
            String withUUID = message + "§§" + client.getUuid();
            byte[] answer = withUUID.getBytes();
            System.out.println(answer.length);

            this.socket.send(
                    new DatagramPacket(answer, answer.length, InetAddress.getByName(client.getIp()), client.getPort())
            );
        }

    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
        this.server.setClient(clients.get(0));
    }
}
