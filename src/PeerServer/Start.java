package PeerServer;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.UUID;


public class Start extends Thread {
    private ArrayList<Client> clients;

    public Start() {
        this.clients = new ArrayList<>();
    }

    @Override
    public void run()  {
        while (true) {
            DatagramSocket server = null;
            try {
                server = new DatagramSocket(9999);
            } catch (SocketException e) {
                e.printStackTrace();
            }
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
                if (client.getIp().equals(clientIP.toString())){
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
                clients.forEach(clientString::append);
                byte[] answer = clientString.toString().getBytes();

                server.send(
                        new DatagramPacket(answer, answer.length, clientIP, clientPort)
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
