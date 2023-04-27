package tcp;

import backend.API;
import backend.Connection;
import backend.Router;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static backend.BackendDependencyInjection.dependencyInjection;

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
