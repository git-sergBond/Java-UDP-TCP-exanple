package udp;

import service.API;
import service.Router;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

import static service.BackendDependencyInjection.dependencyInjection;

public class UDPServer {

    private static final Router router = dependencyInjection();

    public static void main(String[] args) {

        try (DatagramSocket socket = new DatagramSocket(API.PORT)) {
            while (true) {
                DatagramPacket request = listenRequest(socket);

                String url = DatagramPacketUtil.getMessage(request);

                String response = router.route(url);
                byte[] responseByte = response.getBytes(StandardCharsets.UTF_8);

                reply(socket, responseByte, request.getAddress(), request.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DatagramPacket listenRequest(DatagramSocket socket) throws IOException {
        byte[] buffer = new byte[256_000];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        socket.receive(request);
        return request;
    }

    private static void reply(DatagramSocket socket, byte[] responseByte,
                              InetAddress address, int port) throws IOException {
        DatagramPacket reply = new DatagramPacket(
                responseByte,
                responseByte.length,
                address,
                port
        );
        socket.send(reply);
    }
}
