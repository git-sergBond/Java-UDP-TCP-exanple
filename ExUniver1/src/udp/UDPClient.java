package udp;

import client.ClientDependencyInjection;
import client.Sender;
import backend.API;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

import static client.SettingsUtil.readHostFromCommandLineArguments;

public class UDPClient {

    public static void main(String[] args) {
        Sender sender = (String request) -> {
            //If we run the client and server on the local machine.
            //We will face with a problem that we can not open the same port twice
            //for listening incoming messages to the client.
            //That's why I change 6789 (API. PORT) on 777.
            try (DatagramSocket socket = new DatagramSocket(7777)) {
                String host = readHostFromCommandLineArguments(args);
                sendMessage(socket, request, host, API.PORT);

                receiveMessage(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        ClientDependencyInjection clientApp = new ClientDependencyInjection(sender);
        clientApp.run();
    }

    private static void sendMessage(DatagramSocket socket,
                                    String message,
                                    String ip, int port) throws IOException {
        byte[] byteMessage = message.getBytes(StandardCharsets.UTF_8);
        InetAddress host = InetAddress.getByName(ip);

        DatagramPacket request = new DatagramPacket(byteMessage, byteMessage.length, host, port);

        socket.send(request);
    }

    private static void receiveMessage(DatagramSocket socket) throws IOException {
        byte[] buffer = new byte[1000];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);

        String message = DatagramPacketUtil.getMessage(response);
        printMessage(message);
    }

    private static void printMessage(String message) {
        System.out.println("Received: " + message);
    }
}
