package week4.lab2a.tuswadlab42a.service;

import org.springframework.stereotype.Service;
import week4.lab2a.tuswadlab42a.domain.Category;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.dto.CategoryDTO;
import week4.lab2a.tuswadlab42a.repository.CategoryRepository;
import week4.lab2a.tuswadlab42a.repository.RestaurantRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;

    public CategoryService(CategoryRepository categoryRepository, RestaurantRepository restaurantRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public List<Category> getCategoriesByRestaurantId(Long restaurantId) {
        return categoryRepository.findByRestaurantId(restaurantId);
    }

    public Category getCategoryById(Long categoryId) {

        return categoryRepository.getReferenceById(categoryId);
    }

    public Category createCategory(CategoryDTO req) {
        Category newCategory = new Category();
        if(req.getName() != null) newCategory.setName(req.getName());
        if(req.getRestaurantId() != null && restaurantRepository.findById(req.getRestaurantId()).isPresent()) {
            newCategory.setRestaurant(restaurantRepository.findById(req.getRestaurantId()).get());
        }
        return categoryRepository.save(newCategory);
    }

    public Category updateCategory(Long categoryId, CategoryDTO req) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found")); // ensure category exists
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found")); // enforce "update by restaurant" (category must belong to that restaurant)

        if (!category.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RuntimeException("Category does not belong to the given category"); } // update fields (only if provided) if (req.getName() != null)

        if(req.getName() != null) category.setName(req.getName());
        return categoryRepository.save(category);
    }

    public String deleteCategory(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        categoryRepository.delete(category);
        return "Category deleted";
    }
}
