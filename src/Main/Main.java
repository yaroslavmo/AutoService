package Main;


import enterprise.AutoService;
import person.Client;
import repository.categories.CategoriesRepository;
import repository.categories.HardcodedCategories;
import repository.clients.ClientsRepository;
import repository.clients.HardcodedClients;
import repository.services.HardcodedServices;
import repository.services.ServicesRepository;

public class Main {
    public static void main(String[] args) {
        ClientsRepository clients = new HardcodedClients();
        HardcodedCategories categories = new HardcodedCategories();
        ServicesRepository services = new HardcodedServices(categories);
        categories.addServices(services);
        clients.getClientList().forEach(client -> System.out.println(client.getName()));
        System.out.println();
        categories.getCategoryList().forEach(System.out::println);
        System.out.println();
        services.getServiceList().forEach(System.out::println);

        System.out.println(services.getServiceList());
//        AutoService autoservice = new AutoService(clients.getClientList(), services.getServiceList());
    }


}
