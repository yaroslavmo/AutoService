package repository.categories;

import enterprise.Category;
import enterprise.Service;
import repository.services.HardcodedServices;
import repository.services.ServicesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardcodedCategories implements CategoriesRepository{

    private ArrayList<Category> categories;

    public HardcodedCategories() {
        ArrayList<Category> categoryList;
        Category category1 = new Category("Engine");
        Category category2 = new Category("Wheels");
        Category category3 = new Category("Electrical and electronics");
        categoryList = new ArrayList<>(Arrays.asList(category1, category2, category3));
        this.categories = categoryList;
    }

    public void addServices(ServicesRepository services) {
        for (Category category : this.getCategoryList()){
            for(Service service : services.getServiceList()){
                if (service.getServiceCategory().getName().equals(category.getName())){
//                    service.setServiceCategory(category);
                    category.addServiceToCategory(service);
                }
            }
        }
    }

    @Override
    public List<Category> getCategoryList() {
        return this.categories;
    }

}
