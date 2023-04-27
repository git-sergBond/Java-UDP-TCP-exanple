package client;

public class ClientDependencyInjection {

    private Sender sender;

    public ClientDependencyInjection(Sender sender) {
        this.sender = sender;
    }

    public ClientApplication getApplication() {
        return new ClientApplication(sender);
    }
}
