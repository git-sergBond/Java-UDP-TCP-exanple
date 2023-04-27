package backend;

public class API {

    public static int PORT = 6789;

    public static final String GET_CLIENT_BY_ACCOUNT_ID = "GET /client/accountId/";

    public static final String GET_ALL_CLIENTS = "GET /clients";

    public static String buildGetClientByAccountId(String accountId) {
        return GET_CLIENT_BY_ACCOUNT_ID + accountId;
    }
}
