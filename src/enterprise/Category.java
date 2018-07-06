package enterprise;

import java.util.ArrayList;

public class Category {
    public String name;
    private ArrayList<Service> categoryServices;

    public Category(String name, ArrayList<Service> categoryServices) {
        this.name = name;
        this.categoryServices = categoryServices;
    }

    public Category(String name, Service categoryService) {
        this.name = name;
        addServiceToCategory(categoryService);
    }


    private void addServiceToCategory(Service service){
        this.categoryServices.add(service);
    }

    public ArrayList<Service> getCategoryServices() {
        return categoryServices;
    }

    public void setCategoryServices(ArrayList<Service> categoryServices) {
        this.categoryServices = categoryServices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
