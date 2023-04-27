package tcp;

import repository.ClientReposiory;
import tcp.service.Connection;
import tcp.service.Router;
import tcp.service.Serializer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final Router router = dependencyInjection();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7896)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, router);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Router dependencyInjection() {
        ClientReposiory clientReposiory = new ClientReposiory();
        Serializer serializer = new Serializer();
        return new Router(clientReposiory, serializer);
    }
}
