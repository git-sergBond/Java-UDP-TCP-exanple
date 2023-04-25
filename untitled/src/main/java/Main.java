import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Main {
    /**
     * Программа создает сокет,
     * соединяется с сервером (порт 6789), пересылает
     * ему сообщение и ждет ответа.
     * @param args
     */
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(6789);

            sendMessage(socket, "hello", "localhost", 6789);

            receiveMessage(socket);

            socket.close();

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

        System.out.println("Response: " + new String(response.getData()));
    }
}
