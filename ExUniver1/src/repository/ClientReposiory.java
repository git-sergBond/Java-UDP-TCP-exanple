package repository;

import model.Client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static model.AccountType.INDIVIDUAL;
import static model.AccountType.LEGAL_ENTITY;

public class ClientReposiory {

    private Client[] clients = {
            new Client(
                    "FIO_1",
                    1,
                    INDIVIDUAL,
                    new BigDecimal(100)
            ),
            new Client(
                    "FIO_2",
                    2,
                    LEGAL_ENTITY,
                    new BigDecimal(10_000)
            ),
            new Client(
                    "FIO_3",
                    3,
                    LEGAL_ENTITY,
                    new BigDecimal(5_000)
            )
    };

    public Client[] getAllClients() {
        return clients;
    }

    public Optional<Client> getClientById(int accountId){
        return List.of(clients)
                .stream()
                .filter(client -> accountId == client.getAccountId())
                .findFirst();
    }
}
