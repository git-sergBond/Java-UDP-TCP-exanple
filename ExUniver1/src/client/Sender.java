package client;

@FunctionalInterface
public interface Sender {

    void send(String request);
}
