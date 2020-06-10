import PeerServer.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * UDP Peer Server, start to enable peer2peer
 */
public class Main1 {

    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList<>();
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(9999);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {

            DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
            try {
                server.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress clientIP = datagramPacket.getAddress();
            int clientPort = datagramPacket.getPort();
            boolean found = false;
            for(Client client: clients) {
                System.out.println("port: " + clientPort);
                if (client.getIp().equals(clientIP.toString()) && client.getPort() == clientPort) {
                    found = true;
                }
            }
            if (!found) {
                System.out.println("New Client Added");
                String uuid = UUID.randomUUID().toString();
                Client client = new Client(clientIP.toString(), clientPort, uuid);
                clients.add(client);
            }
            try {
                StringBuilder clientString = new StringBuilder();
                response(clients, clientIP, clientPort).forEach(clientString::append);
                byte[] answer = clientString.toString().getBytes();

                server.send(
                        new DatagramPacket(answer, answer.length, clientIP, clientPort)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static ArrayList<Client> response(ArrayList<Client> clients, InetAddress clientIP, int clientPort) {
        Client sender = null;
        int senderPosition = 0;
        for(Client client: clients) {
            senderPosition++;
            System.out.println("port: " + clientPort);
            if (client.getIp().equals(clientIP.toString()) && client.getPort() == clientPort) {
                sender = client;
                break;
            }
        }
        clients.remove(senderPosition - 1);
        clients.add(0, sender);
        return clients;
    }
}
