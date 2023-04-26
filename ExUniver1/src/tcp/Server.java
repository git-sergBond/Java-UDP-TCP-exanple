package tcp;

import tcp.service.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7896)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
