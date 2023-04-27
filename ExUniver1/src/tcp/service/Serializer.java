package tcp.service;

import model.Client;

public class Serializer {

    String serialize(Client client) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        sb.append("\t\"fullName\" : \"");
        sb.append(client.getFullName());
        sb.append("\",\n");

        sb.append("\t\"accountId\" : ");
        sb.append(client.getAccountId());
        sb.append(",\n");

        sb.append("\t\"accountType\" : ");
        sb.append(client.getAccountType().ordinal());
        sb.append(",\n");

        sb.append("\t\"amount\" : ");
        sb.append(client.getAmount());

        sb.append("\n}");
        return sb.toString();
    }
}
