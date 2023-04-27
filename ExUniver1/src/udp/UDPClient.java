package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {

    private static final String GET_CLIENT_BY_ACCOUNT_ID = "GET /client/accountId/";

    private static final String GET_ALL_CLIENTS = "GET /clients";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1) " + GET_ALL_CLIENTS);
            System.out.println("2) " + GET_CLIENT_BY_ACCOUNT_ID);
            System.out.println("Or type \"exit\" for escape program");

            String answer = scanner.nextLine();
            if (answer.startsWith("exit")) {
                break;
            } else if (answer.startsWith("1")) {
                send(GET_ALL_CLIENTS);
            } else if (answer.startsWith("2")) {
                System.out.println("Enter account ID:");
                String accountId = scanner.nextLine();

                send(GET_CLIENT_BY_ACCOUNT_ID + accountId);
            }
        }
    }

    private static void send(String request) {
        try (DatagramSocket socket = new DatagramSocket(7777)) {

            sendMessage(socket, request, "localhost", 6789);

            receiveMessage(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        printMessage(response);
    }

    private static String getMessage(DatagramPacket datagram) {
        return new String(datagram.getData(), 0, datagram.getLength(), StandardCharsets.UTF_8);
    }

    private static void printMessage(DatagramPacket datagram) {
        String message = getMessage(datagram);
        System.out.println("Received: " + message);
    }
}
