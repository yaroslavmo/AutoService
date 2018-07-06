package repository.services;

import enterprise.Service;
import repository.categories.HardcodedCategories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardcodedServices implements ServicesRepository{
    private ArrayList<Service> services;

    public HardcodedServices(HardcodedCategories categories) {
        ArrayList<Service> serviceList;
        Service service1 = new Service("Change oil",120, categories.getCategoryList().get(0));
        Service service2 = new Service("Fuel supply system repair",500,categories.getCategoryList().get(0));
        Service service3 = new Service("Transmission system repair",600, categories.getCategoryList().get(0));
        Service service4 = new Service("Auidio/Video devices",400, categories.getCategoryList().get(2));
        Service service5 = new Service("Ignition electronic system",800,categories.getCategoryList().get(2));
        Service service6 = new Service("Lighting and signaling system",300, categories.getCategoryList().get(2));
        Service service7 = new Service("Braking system",120, categories.getCategoryList().get(1));
        Service service8 = new Service("Tires repair",500,categories.getCategoryList().get(1));
        Service service9 = new Service("Steering wheel",600, categories.getCategoryList().get(1));
        serviceList = new ArrayList<Service>(Arrays.asList(service1, service2, service3, service4, service5, service6, service7, service8, service9));
        this.services = serviceList;

    }

    @Override
    public List<Service> getServiceList() {

        return this.services;
    }
}
