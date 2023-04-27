package service;

import repository.ClientReposiory;

public class BackendDependencyInjection {

    public static Router dependencyInjection() {
        ClientReposiory clientReposiory = new ClientReposiory();
        Serializer serializer = new Serializer();
        Controller controller = new Controller(clientReposiory, serializer);
        return new Router(controller);
    }
}
