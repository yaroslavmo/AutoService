package repository.categories;

import enterprise.Category;
import repository.services.HardcodedServices;
import repository.services.ServicesRepository;

import java.util.List;

public interface CategoriesRepository {
        List<Category> getCategoryList();
    }
