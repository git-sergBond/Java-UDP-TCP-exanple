package tcp.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket socket;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        this.start();
    }

    @Override
    public void run() {
        try {
            String data = in.readUTF();
            out.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
