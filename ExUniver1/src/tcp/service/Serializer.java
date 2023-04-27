package tcp.service;

import model.Client;

public class Serializer {

    String serialize(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\t\"fullName\" : \"");
        sb.append(client.getFullName());
        sb.append("\"");
        sb.append("\n}");
        return sb.toString();
    }
}
