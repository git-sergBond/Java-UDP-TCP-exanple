package tcp.service;

import model.Client;
import repository.ClientReposiory;

import java.util.Optional;

public class Router {

    private final String GET_CLIENT_BY_ACCOUNT_ID = "GET /client/accountId/";

    private final String GET_ALL_CLIENTS = "GET /clients";

    private final String ERROR_404 = "404 error: not found ";

    private final String ERROR_400 = "400 error: bad request ";

    private final String ERROR_500 = "500 error: internal server error ";

    private ClientReposiory clientReposiory;

    private Serializer serializer;

    public Router(ClientReposiory clientReposiory, Serializer serializer) {
        this.clientReposiory = clientReposiory;
        this.serializer = serializer;
    }

    public String route (String url) {
        if (GET_ALL_CLIENTS.equals(url)) {
            System.out.println("accountId");
            return "1";
        } else if (url.startsWith(GET_CLIENT_BY_ACCOUNT_ID)) {
            String accountId = url.substring(GET_CLIENT_BY_ACCOUNT_ID.length());
            if (accountId.isEmpty()) {
                return ERROR_400 + "accountId is empty";
            }

            int accountIdInt;
            try {
                accountIdInt = Integer.parseInt(accountId);
            } catch (NumberFormatException e) {
                return ERROR_400 + "accountId is not number";
            }


            Optional<Client> resp;
            try {
                resp = clientReposiory.getClientById(accountIdInt);
            } catch (Exception e) {
                return ERROR_500 + e.getMessage();
            }

            if (resp.isEmpty()) {
                return ERROR_404;
            }

            Client client = resp.get();

            try {
                return serializer.serialize(client);
            } catch (Exception e) {
                return ERROR_500 + e.getMessage();
            }
        }

        return ERROR_400 + "no such method";
    }

}
