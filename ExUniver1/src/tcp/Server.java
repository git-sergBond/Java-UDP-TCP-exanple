package tcp;

import repository.ClientReposiory;
import service.Connection;
import service.Controller;
import service.Router;
import service.Serializer;

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
        Controller controller = new Controller(clientReposiory, serializer);
        return new Router(controller);
    }
}
