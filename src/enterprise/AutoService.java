package enterprise;

import person.Client;

import java.util.ArrayList;
import java.util.List;

public class AutoService {
    private List<Client> clients;
    private List<Service> services;
    private List<Bill> bills;

    public AutoService(List<Client> clients, List<Service> services) {
        this.clients = clients;
        this.services = services;
        this.bills = new ArrayList<>();
    }

    public AutoService(Client client, Service service) {
        this.clients = new ArrayList<>();
        this.services = new ArrayList<>();
        this.bills = new ArrayList<>();
        this.clients.add(client);
        this.services.add(service);
        this.bills = new ArrayList<>();
    }

    public List<Bill> getBills() {
        return bills;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Service> getServices() {
        return services;
    }

    public void makeBill(Client billClient, ArrayList<Service> billServices) {
        Bill bill = new Bill(billClient, billServices);
        bill.addService(services.get(0));
        this.bills.add(bill);
        bill.print();
    }

    public void deleteBills() {
        this.bills = new ArrayList<>();
    }

    public void deleteBill(Bill bill) {
        this.bills.remove(bill);
    }
    
}
