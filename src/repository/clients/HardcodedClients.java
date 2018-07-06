package repository.clients;

import person.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardcodedClients implements ClientsRepository {
    @Override
    public List<Client> getClientList() {
        Client client1 = new Client("Bogdan Movchan");
        Client client2 = new Client("Egor Badyanov");
        Client client3 = new Client("Igor Umanets");

        return new ArrayList<>(Arrays.asList(client1, client2, client3));
    }
}
