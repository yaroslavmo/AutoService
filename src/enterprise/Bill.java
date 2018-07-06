package enterprise;

import person.Client;

import java.util.*;
import java.util.stream.Collectors;

public class Bill {
    private final Client client;
    private double totalCost = 0;
    private final ArrayList<Service> billServices;
    private  Set<Category> billCategories;
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

    public void addService(Service service){
        this.billServices.add(service);
        setBillCategories();
        countTotal();

    }

    private void setBillCategories(){
        this.billCategories = this.billServices.stream()
                .map(Service::getServiceCategory).collect(Collectors.toSet());
    }

    private void countTotal(){
        this.CategoriesTotal = new HashMap<>();
        this.totalCost = billServices.stream()
                .mapToDouble(Service::getPrice)
                .sum();
        billCategories
                .forEach(category ->
                        CategoriesTotal.put(category, countTotalByCategory(category, this.getBillServices())));

    }

    private Double countTotalByCategory(Category category, ArrayList<Service> services){
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
        String output = "Bill" + "\n" +
                "client=" + client + "\n";

        for(Map.Entry <Category,Double> entry : getCategoriesTotal().entrySet()){
            for(Service service : getBillServices()){
                if(service.getServiceCategory().getName().equals(entry.getKey().getName())){
                    output += service.getServiceName() + " ------------ " + service.getPrice() + "\n";
                }
            }
            output += "\n" + entry.getKey().getName().toUpperCase() + " ------------ " + entry.getValue().toString() + "\n\n\n";
        }
        output += "Total: " + totalCost + "\n";

        return output;
    }
}
