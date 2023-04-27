package service;

import model.Client;
import repository.ClientReposiory;

import java.util.Optional;

public class Controller {

    private ClientReposiory clientReposiory;

    private Serializer serializer;

    public Controller(ClientReposiory clientReposiory, Serializer serializer) {
        this.clientReposiory = clientReposiory;
        this.serializer = serializer;
    }

    public String getAllClients() {
        Client[] resp;
        try {
            resp = clientReposiory.getAllClients();
        } catch (Exception e) {
            return Error.ERROR_500 + e.getMessage();
        }

        if (resp == null) {
            return Error.ERROR_404;
        }

        try {
            return serializer.serialize(resp);
        } catch (Exception e) {
            return Error.ERROR_500 + e.getMessage();
        }
    }

    public String getClientByAccountId(String url) {
        String accountId = url.substring(API.GET_CLIENT_BY_ACCOUNT_ID.length());
        if (accountId.isEmpty()) {
            return Error.ERROR_400 + "accountId is empty";
        }

        int accountIdInt;
        try {
            accountIdInt = Integer.parseInt(accountId);
        } catch (NumberFormatException e) {
            return Error.ERROR_400 + "accountId is not number";
        }


        Optional<Client> resp;
        try {
            resp = clientReposiory.getClientById(accountIdInt);
        } catch (Exception e) {
            return Error.ERROR_500 + e.getMessage();
        }

        if (resp.isEmpty()) {
            return Error.ERROR_404;
        }

        Client client = resp.get();

        try {
            return serializer.serialize(client);
        } catch (Exception e) {
            return Error.ERROR_500 + e.getMessage();
        }
    }
}
