import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {
        int port = 7896;
        try (Socket socket = new Socket("localhost", port)) {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeUTF("HELLO");

            String data = inputStream.readUTF();
            System.out.println("Received: " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
