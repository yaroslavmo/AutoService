package enterprise;

import person.Client;

import java.util.*;
import java.util.stream.Collectors;

public class Bill {
    private final Client client;
    private double totalCost = 0;
    private final ArrayList<Service> billServices;
    private Set<Category> billCategories;
    private Map<Category, Double> CategoriesTotal;
//    private ArrayList<Service> billServices;


    public Bill(Client client, ArrayList<Service> billServices) {
        this.client = client;
        this.billServices = billServices;
        setBillCategories();
        countTotal();
    }

    public Bill(Client client, Service billService) {
        this.client = client;
        //this.billServices = new ArrayList<>();
        this.billServices = new ArrayList<>(Collections.singletonList(billService));
        setBillCategories();
        countTotal();
    }

    public Client getClient() {
        return client;
    }

    public Map<Category, Double> getCategoriesTotal() {
        return CategoriesTotal;
    }

    public void addService(Service service) {
        this.billServices.add(service);
        setBillCategories();
        countTotal();

    }

    private void setBillCategories() {
        this.billCategories = this.billServices.stream()
                .map(Service::getServiceCategory).collect(Collectors.toSet());
    }

    private void countTotal() {
        this.CategoriesTotal = new HashMap<>();
        this.totalCost = billServices.stream()
                .mapToDouble(Service::getPrice)
                .sum();
        billCategories
                .forEach(category ->
                        CategoriesTotal.put(category, countTotalByCategory(category, this.getBillServices())));

    }

    private Double countTotalByCategory(Category category, ArrayList<Service> services) {
        return services.stream()
                .filter(service -> service.getServiceCategory().getName().equals(category.getName()))
                .mapToDouble(Service::getPrice)
                .sum();
    }

    public ArrayList<Service> getBillServices() {
        return billServices;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("   ********Bill********" + "\n" +
                "   Client: " + client.getName() + "\n");

        for (Map.Entry<Category, Double> entry : getCategoriesTotal().entrySet()) {
            for (Map.Entry<Service,Long> service : countRepeatedServices(getBillServices()).entrySet()) {
                if (service.getKey().getServiceCategory().getName().equals(entry.getKey().getName())) {
                    String amount = service.getValue() > 1 ? "x" + service.getValue() : "";
                    output.append(" ------------").append(service.getKey().getServiceName()).append("  ").append(service.getKey().getPrice()).append("  ").append(amount).append("\n");
                }
            }
            output.append("\n" + " ------------").append(entry.getKey().getName().toUpperCase()).append("  ").append(entry.getValue().toString()).append("\n\n\n");
        }
        output.append("Total: ").append(totalCost).append("\n");

        return output.toString();
    }

    private Map<Service, Long> countRepeatedServices(ArrayList<Service> services){
        return services.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public void print() {
        System.out.println(this);
    }
}
