package tcp;

import client.ClientDependencyInjection;
import client.Sender;
import backend.API;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static client.SettingsUtil.readHostFromCommandLineArguments;

public class TCPClient {

    public static void main(String[] args) {
        Sender sender = (String request) -> {
            String host = readHostFromCommandLineArguments(args);
            try (Socket socket = new Socket(host, API.PORT)) {
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeUTF(request);

                String response = inputStream.readUTF();
                printMessage(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        ClientDependencyInjection clientApp = new ClientDependencyInjection(sender);
        clientApp.getApplication().run();
    }

    private static void printMessage(String message) {
        System.out.println("Received: " + message);
    }
}
