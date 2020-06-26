package app.network.helper;

import app.network.Server;
import app.network.events.NetworkResponseEvent;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.Event;
import famework.event.EventHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@Service
public class Tcp {
    BufferedInputStream in;

    @Inject
    private EventHandler eventHandler;
    @Inject
    private PrintStream out;
    @Inject
    private Server server;


    private final String hostname;
    private final int port;
    private Socket socket;

    public Tcp(ConfigBag configBag, BufferedInputStream in) {
        this.in = in;
        this.hostname = configBag.props.getProperty("hostname");
        this.port = Integer.parseInt(configBag.props.getProperty("port"));
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(hostname);
            socket = new Socket(ip, port);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            receiver();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void push(Event event) {
        try {
            sender(event);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void receiver() throws IOException, ClassNotFoundException {
        InputStream socketInputStream = socket.getInputStream();
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(socketInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        NetworkResponseEvent requestEvent = (NetworkResponseEvent) inputStream.readObject();
        if (requestEvent == null) return;
        if (requestEvent.getEvent() == null) return;

        // second read?
        ObjectInputStream finalInputStream = inputStream;

        try {
            // read the message sent to this client
            NetworkResponseEvent message = (NetworkResponseEvent) finalInputStream.readObject();
            System.out.println("New Message");
            System.out.println(message.getName());
        } catch (IOException | ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    private void sender(Event event) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(event);
    }
}
