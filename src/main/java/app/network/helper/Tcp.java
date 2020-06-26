package app.network.helper;

import app.network.Server;
import app.network.events.NetworkRequestEvent;
import app.network.events.NetworkResponseEvent;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;
import famework.event.EventHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

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
    private ObjectOutputStream outputStream;
    private InputStream socketInputStream;

    private Boolean connected = false;

    public Tcp(ConfigBag configBag, BufferedInputStream in) {
        this.in = in;
        this.hostname = configBag.props.getProperty("hostname");
        this.port = Integer.parseInt(configBag.props.getProperty("port"));


    }

    public void connect() {
        System.out.println("Connecting to Server");
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName(hostname);
            socket = new Socket(ip, port);
            socketInputStream = socket.getInputStream();


            outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            connected = true;

            receiver(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void push(NetworkRequestEvent event) {
        try {
            sender(event);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void receiver(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        if (socket == null) return;
        while (true) {
            try {

                NetworkResponseEvent requestEvent = (NetworkResponseEvent) inputStream.readObject();
                System.out.println("got message");
                eventHandler.push(requestEvent.getResponse());
//                 if (requestEvent == null) return;
//                    if (requestEvent.getEvent() == null) return;

            } catch (IOException emptyStreamException) {

            }
        }
//
//        // second read?
//        ObjectInputStream finalInputStream = inputStream;
//
//        try {
//            // read the message sent to this client
//            NetworkResponseEvent message = (NetworkResponseEvent) finalInputStream.readObject();
//            System.out.println("New Message");
//            System.out.println(message.getName());
//        } catch (IOException | ClassNotFoundException e) {
//
//            e.printStackTrace();
//        }
    }

    private void sender(NetworkRequestEvent event) throws IOException {
        outputStream.writeObject(event);
    }
}
