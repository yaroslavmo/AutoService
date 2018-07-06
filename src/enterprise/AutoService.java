package enterprise;

import person.Client;

import java.util.ArrayList;

public class AutoService {
    private ArrayList<Client> clients;
    private ArrayList<Service> services;
    private ArrayList<Bill> bills;

    public AutoService(ArrayList<Client> clients, ArrayList<Service> services, ArrayList<Bill> bills) {
        this.clients = clients;
        this.services = services;
        this.bills = bills;
    }

    public AutoService(Client client, Service service, Bill bill) {
        this.clients.add(client);
        this.services.add(service);
        this.bills.add(bill);
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void makeBill(Client billClient, ArrayList<Service> billServices)  {
        Bill bill = new Bill(billClient,billServices);
        this.bills.add(bill);
    }
}
