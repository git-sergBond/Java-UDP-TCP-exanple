package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static final String GET_CLIENT_BY_ACCOUNT_ID = "GET /client/accountId/";

    private static final String GET_ALL_CLIENTS = "GET /clients";

    public static void main(String[] args) {
        int port = 7896;
        try (Socket socket = new Socket("localhost", port)) {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeUTF(GET_CLIENT_BY_ACCOUNT_ID+"2");

            String data = inputStream.readUTF();
            System.out.println("Received: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
