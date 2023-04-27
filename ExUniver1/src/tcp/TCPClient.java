package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

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
        int port = 7896;
        try (Socket socket = new Socket("localhost", port)) {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(request);
            String response = inputStream.readUTF();
            System.out.println("Received: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
