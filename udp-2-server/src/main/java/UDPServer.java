import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class UDPServer {
    /**
     * Программа (сервер) создает сокет и обслуживает запросы клиента.
     *
     * TODO java udp Address already in use: Cannot bind
     * TODO https://www.youtube.com/watch?v=DMoz4VwwJgg
     *
     * @param args
     */
    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(6789)) {

            byte[] buffer = new byte[1000];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                printMessage(request);

                DatagramPacket reply = new DatagramPacket(
                        request.getData(),
                        request.getLength(),
                        request.getAddress(),
                        request.getPort()
                );
                socket.send(reply);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMessage(DatagramPacket datagram) {
        String message = new String(datagram.getData(), 0, datagram.getLength(), StandardCharsets.UTF_8);
        System.out.println("Message: " + message);
    }
}
