package tcp;

import service.API;
import service.Connection;
import service.Router;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static service.BackendDependencyInjection.dependencyInjection;

public class TCPServer {

    private static final Router router = dependencyInjection();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(API.PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket, router);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
