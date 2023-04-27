package udp;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;

public class DatagramPacketUtil {

    public static String getMessage(DatagramPacket datagram) {
        return new String(datagram.getData(), 0, datagram.getLength(), StandardCharsets.UTF_8);
    }
}
