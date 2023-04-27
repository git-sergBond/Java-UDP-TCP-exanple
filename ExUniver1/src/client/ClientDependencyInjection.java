package client;

import service.API;

import java.util.Scanner;

public class ClientDependencyInjection {

    private Sender sender;

    public ClientDependencyInjection(Sender sender) {
        this.sender = sender;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1) " + API.GET_ALL_CLIENTS);
            System.out.println("2) " + API.GET_CLIENT_BY_ACCOUNT_ID);
            System.out.println("Or type \"exit\" for escape program");

            String answer = scanner.nextLine();
            if (answer.startsWith("exit")) {
                break;
            } else if (answer.startsWith("1")) {
                sender.send(API.GET_ALL_CLIENTS);
            } else if (answer.startsWith("2")) {
                System.out.println("Enter account ID:");
                String accountId = scanner.nextLine();

                sender.send(API.GET_CLIENT_BY_ACCOUNT_ID + accountId);
            }
        }
    }
}
