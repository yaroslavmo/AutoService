package enterprise;

import person.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bill {
    private final Client client;
    private double totalCost = 0;
    private final ArrayList<Service> billServices;

    public Bill(Client client, ArrayList<Service> billServices) {
        this.client = client;
        this.billServices = billServices;
    }

    public Bill(Client client, Service billService) {
        this.client = client;
        this.billServices = new ArrayList<>(Collections.singletonList(billService));
        countTotalByServices();
    }

    public Client getClient() {
        return client;
    }

    public void addService(Service service){
        this.billServices.add(service);
        this.countTotalByServices();

    }

    private void countTotalByServices(){
        this.totalCost = billServices.stream().mapToDouble(Service::getPrice).sum();
    }

    private void countTotalByCategory(){

    }

    public ArrayList<Service> getBillServices() {
        return billServices;
    }
}
