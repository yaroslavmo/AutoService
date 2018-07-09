package enterprise;

import enterprise.discountSystem.Discount;
import person.Client;

import java.util.*;
import java.util.stream.Collectors;

public class Bill {
    private final Client client;
    private double totalCost = 0;
    private final ArrayList<Service> billServices;
    private boolean closed;
    private double totalDiscount;
    private Set<Category> billCategories;
    private Map<Category, Double> CategoriesTotal;
//    private ArrayList<Service> billServices;


    public Bill(Client client, ArrayList<Service> billServices) {
        this.client = client;
        this.billServices = billServices;
        this.totalDiscount = 0;
        setBillCategories();
        countTotal();
    }

    public Bill(Client client, Service billService) {
        this.client = client;
        //this.billServices = new ArrayList<>();
        this.totalDiscount = 0;
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

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    private void setBillCategories() {
        this.billCategories = this.billServices.stream()
                .map(Service::getServiceCategory).collect(Collectors.toSet());
    }

    private void countTotal() {
        double categoryDiscount;
        this.CategoriesTotal = new HashMap<>();
        billCategories
                .forEach(category ->
                        CategoriesTotal.put(category, countTotalByCategory(category, this.getBillServices())));
        this.totalCost = billServices.stream()
                .mapToDouble(Service::getPrice)
                .sum();

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

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void countTotalDiscount() {
        for (Map.Entry<Category, Double> entry : getCategoriesTotal().entrySet()) {
            this.totalDiscount += entry.getKey().getDiscount().getAmount();
        }
    }

    private Map<Service, Long> countRepeatedServices(ArrayList<Service> services) {
        return services.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    private String countCategoryDiscount(double categoryTotal, double discount) {
        return String.format("  %f", categoryTotal * discount);
    }

    private String printDiscount(Category category, double categoryTotal) {
        if (category.hasDiscount()) {
            return String.format("Discount ( %s -- %s)", category.getDiscount().getDiscountName(),
                    this.countCategoryDiscount(category.getDiscount().getAmount(), categoryTotal));
        }
        return "";
    }


    private String createBillStringToPrint() {
String s;
        String output = String.format("   ********Bill******** \n   Client:%s \n", client.getName());
        for (Map.Entry<Category, Double> entry : getCategoriesTotal().entrySet()) {
            for (Map.Entry<Service, Long> service : countRepeatedServices(getBillServices()).entrySet()) {
                if (service.getKey().getServiceCategory().getName().equals(entry.getKey().getName())) {
                    String amount = service.getValue() > 1 ? "x" + service.getValue() : "";
                    //output = String.format(output + " ------------  %s   \n", service.getKey().getServiceName());
                            //service.getKey().getPrice(), amount);
                }
            }
            output += "\n  ------------ %1$s   %2$s  %3$s\n\n\n";
            output = String.format(output ,
                    entry.getKey().getName().toUpperCase(),
                    entry.getValue(),
                    printDiscount(entry.getKey(), entry.getValue())
                    );
        }
        output = String.format(output + "\n  Total: %.2f", this.totalCost);

        return output;
    }

    @Override
    public String toString() {
        return createBillStringToPrint();
    }


}
