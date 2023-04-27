package tcp.service;

public class Router {

    private Controller controller;

    public Router(Controller controller) {
        this.controller = controller;
    }

    public String route (String url) {
        if (API.GET_ALL_CLIENTS.equals(url)) {
            return controller.getAllClients();
        } else if (url.startsWith(API.GET_CLIENT_BY_ACCOUNT_ID)) {
            return controller.getClientByAccountId(url);
        }

        return Error.ERROR_400 + "no such method";
    }

}
