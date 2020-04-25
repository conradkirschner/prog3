package network.model;

import PeerServer.Client;
import app.EventStream;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread {
    private EventStream eventStream;
    private DatagramSocket socket;
    private Client client;
    boolean started = false;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEventStream(EventStream eventStream) {
        this.eventStream = eventStream;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    public void run() {
         InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName( "localhost" );
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket packet = new DatagramPacket( "".getBytes(), "".getBytes().length, inetAddress, 9999 );
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        receiver();

    }

    public void inputSwitch(byte[] input) {
        String clients = new String(input).split("/")[0];
        if (clients.equals("")){
            this.eventStream.pushData("network:connect", new String(input));
            return;
        }
        if(new String(input).split("§§")[0].equals("alive")) return; // heartbeat
        String command = new String(input).split("##")[0];
        String data = new String(input).split("##")[1].split("§§")[0];
        String uuid = new String(input).split("##")[1].split("§§")[1];
        if (uuid.trim().equals(this.client.getUuid().trim())) return;
        this.eventStream.pushData(command, data);

    }

    private DatagramPacket receiver() {
        while (true) {
            DatagramPacket response = new DatagramPacket( new byte[1024*15], 1024*15 );
            try {
                socket.receive( response );
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetAddress address = response.getAddress();
            int         port    = response.getPort();
            int         len     = response.getLength();
            byte[]      data    = response.getData();
            System.out.println("recived " + new String(data));

            this.inputSwitch(data);
        }
    }
}
