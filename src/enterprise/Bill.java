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
        double billDiscount = 0;
        this.CategoriesTotal = new HashMap<>();
        billCategories
                .forEach(category ->
                        CategoriesTotal.put(category, countTotalByCategory(category, this.getBillServices())));
//        billDiscount = this.CategoriesTotal.entrySet().stream().mapToDouble(Double::);
        for (Double d : this.CategoriesTotal.values()) {
            billDiscount += d;
        }
        System.out.println(billDiscount);
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

    private String countCategoryDiscount(double discount, double categoryTotal) {
        return String.format("  %s", categoryTotal * discount);
    }

    private String printDiscount(Category category, double categoryTotal) {
        if (category.hasDiscount()) {
            return String.format("  Discount ( %s -- %s)", category.getDiscount().getDiscountName(),
                    this.countCategoryDiscount(category.getDiscount().getAmount(), categoryTotal));
        }
        return "";
    }


    private String createBillStringToPrint() {
        StringBuilder output = new StringBuilder("\n\n   ********Bill********" + "\n" +
                "   Client: " + client.getName() + "\n");

        for (Map.Entry<Category, Double> categoryAndTotal : getCategoriesTotal().entrySet()) {
            for (Map.Entry<Service, Long> service : countRepeatedServices(getBillServices()).entrySet()) {
                if (service.getKey().getServiceCategory().getName().equals(categoryAndTotal.getKey().getName())) {
                    String amount = service.getValue() > 1 ? "x" + service.getValue() : "";
                    output.append(" ------------").append(service.getKey().getServiceName()).append("  ")
                            .append(service.getKey().getPrice()).append("  ").append(amount).append("\n");
                }
            }
            output.append("\n" + " ------------").append(categoryAndTotal.getKey().getName().toUpperCase())
                    .append("  ").append(categoryAndTotal.getValue().toString())
                    .append(printDiscount(categoryAndTotal.getKey(), categoryAndTotal.getValue())).append("\n\n\n");
        }
        output.append("Total: ").append(totalCost).append("\n");

        return output.toString();
    }


//        String output = String.format("   ********Bill******** \n   Client:%s \n", client.getName());
//        for (Map.Entry<Category, Double> entry : getCategoriesTotal().entrySet()) {
//            for (Map.Entry<Service, Long> service : countRepeatedServices(getBillServices()).entrySet()) {
//                if (service.getKey().getServiceCategory().getName().equals(entry.getKey().getName())) {
//                    String amount = service.getValue() > 1 ? "x" + service.getValue() : "";
////                    output = String.format(output + " ------------   %s \n",
////                            service.getKey().getServiceName(),
////                            service.getKey().getPrice(),
////                            amount));
//                }
//            }
//            System.out.println(output) ;
//            output = String.format(output + "\n  ------------ %1$s   %2$s  %3$s\n\n\n" ,
//                    entry.getKey().getName().toUpperCase(),
//                    entry.getValue(),
//                    printDiscount(entry.getKey(), entry.getValue()).toString()
//                    );
//        }
//        output = String.format(output + "\n  Total: %.2f", this.totalCost);
//
//        return output;
//    }

    @Override
    public String toString() {
        return createBillStringToPrint();
    }


}
