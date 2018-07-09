package Main;


import enterprise.AutoService;
import enterprise.Bill;
import enterprise.Service;
import person.Client;
import repository.categories.CategoriesRepository;
import repository.categories.HardcodedCategories;
import repository.clients.ClientsRepository;
import repository.clients.HardcodedClients;
import repository.discounts.HardcodedDiscounts;
import repository.services.HardcodedServices;
import repository.services.ServicesRepository;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientsRepository clientsRepo = new HardcodedClients();
        HardcodedCategories categoriesRepo = new HardcodedCategories();
        HardcodedDiscounts discountsRepo = new HardcodedDiscounts();
        ServicesRepository servicesRepo = new HardcodedServices(categoriesRepo);
        categoriesRepo.addServices(servicesRepo);

        AutoService autoservice = new AutoService(clientsRepo.getClientList(), servicesRepo.getServiceList());

        ArrayList<Service> servicesToBill = new ArrayList<>(Arrays.asList(servicesRepo.getServiceList().get(5),
                servicesRepo.getServiceList().get(8),
                servicesRepo.getServiceList().get(6)));
        ArrayList<Service> servicesToBill1 = new ArrayList<>(Arrays.asList(servicesRepo.getServiceList().get(3),
                servicesRepo.getServiceList().get(7),
                servicesRepo.getServiceList().get(8)));
        ArrayList<Service> servicesToBill2 = new ArrayList<>(Arrays.asList(servicesRepo.getServiceList().get(2),
                servicesRepo.getServiceList().get(0),
                servicesRepo.getServiceList().get(1)));
        categoriesRepo.getCategoryList().get(0).setDiscount(discountsRepo.getDiscountList().get(0));
        categoriesRepo.getCategoryList().get(1).setDiscount(discountsRepo.getDiscountList().get(2));
        categoriesRepo.getCategoryList().get(2).setDiscount(discountsRepo.getDiscountList().get(1));
        autoservice.makeBill(clientsRepo.getClientList().get(0), servicesToBill);
        autoservice.makeBill(clientsRepo.getClientList().get(2), servicesToBill1);
        autoservice.makeBill(clientsRepo.getClientList().get(1), servicesToBill2);


        autoservice.getCurrentBills().get(2).addService(servicesRepo.getServiceList().get(2));
        autoservice.closeAllBills();
        autoservice.getJournal().getAllBills().forEach(autoservice::print);

    }


}
