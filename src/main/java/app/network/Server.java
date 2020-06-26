package app.network;

import app.network.events.NetworkRequestEvent;
import app.network.events.NetworkResponseEvent;
import famework.annotation.Inject;
import famework.annotation.Service;
import famework.configReader.ConfigBag;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

@Service
public class Server {
    private boolean isUDP;
    private boolean running;
    private int serverPort;
    private ArrayList<Socket> clients;

    @Inject
    private PrintStream out;

    public Server(ConfigBag configBag) {
        isUDP = configBag.props.getProperty("protocol").equals("udp");
        serverPort = Integer.parseInt(configBag.props.getProperty("serverPort"));
        clients = new ArrayList<>();
        running = false;
    }

    /**
     * this will start a new server thread on specified serverPort
     * @return
     * @throws IOException
     */
    public int run() throws IOException {
        if (running) return -1;

                // do stuff
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(serverPort, 0, InetAddress.getByName("0.0.0.0"));
                    running=true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println("ServerSocket - startet");
                while (true) {
                    Socket socket = null; // blocking call, this will wait until a connection is attempted on this port.
                    try {
                        socket = serverSocket.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Socket finalSocket = socket;
                    ServerSocket finalServerSocket = serverSocket;
                    (new Thread() {
                        public void run() {
                            // do stuff
                            try{
                                getRequest();
                            } catch (SocketException e) {
                                e.printStackTrace();
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        private void getRequest() throws IOException, ClassNotFoundException, SocketException {
                            System.out.println("Connection from " + finalSocket + "!");

                            // get the input stream from the connected socket
                            InputStream inputStream = finalSocket.getInputStream();
                            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                            NetworkRequestEvent event = (NetworkRequestEvent) objectInputStream.readObject();
                            System.out.println("Received Network Request");
                            System.out.println("forward to client");
                            clients.add(finalSocket);
                            for(Socket client:clients) {
                                if(!client.equals(finalSocket) || true ) {
                                    // get the output stream from the socket.
                                    if (finalSocket.isClosed()) {
                                        continue;
                                    }

                                    OutputStream outputStream = finalSocket.getOutputStream();
                                    // create an object output stream from the output stream so we can send an object through it
                                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                                    System.out.println("Sending messages to the ServerSocket" + finalSocket );
                                    objectOutputStream.writeObject(new NetworkResponseEvent(event.getEvent()));
                                }
                            }
                            // Keep socket open until client exits
                            }
                    }).start();
                }
    }

}
