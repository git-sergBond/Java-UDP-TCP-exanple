package tcp.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket socket;
    private final Router router;

    public Connection(Socket socket, Router router) throws IOException {
        this.socket = socket;
        this.router = router;

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        this.start();
    }

    @Override
    public void run() {
        try {
            String request = in.readUTF();

            String response = router.route(request);

            out.writeUTF(response);
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
