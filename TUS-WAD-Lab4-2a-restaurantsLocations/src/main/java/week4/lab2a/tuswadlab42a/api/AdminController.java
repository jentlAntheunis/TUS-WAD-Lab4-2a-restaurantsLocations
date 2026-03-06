package week4.lab2a.tuswadlab42a.api;

import org.springframework.web.bind.annotation.*;
import week4.lab2a.tuswadlab42a.domain.Category;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.dto.UpdateCategoryRequest;
import week4.lab2a.tuswadlab42a.dto.UpdateLocationRequest;
import week4.lab2a.tuswadlab42a.dto.UpdateRestaurantRequest;
import week4.lab2a.tuswadlab42a.service.CategoryService;
import week4.lab2a.tuswadlab42a.service.LocationService;
import week4.lab2a.tuswadlab42a.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
@CrossOrigin(origins="http://localhost:5173")
public class AdminController {
    private final LocationService locationService;
    private final RestaurantService restaurantService;
    private final CategoryService categoryService;

    public AdminController(
            LocationService locationService,
            RestaurantService restaurantService,
            CategoryService categoryService
    ) {
        this.locationService = locationService;
        this.restaurantService = restaurantService;
        this.categoryService = categoryService;
    }

    // Restaurants
    @GetMapping("restaurants")
    public List<Restaurant> getRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.findById(id);
    }

    @PostMapping("restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        System.out.println(restaurant.toString());
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("restaurants/{id}")
    public Restaurant updateRestaurant(
            @PathVariable long id,
            @RequestBody UpdateRestaurantRequest restaurant
    ) {
        return  restaurantService.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("restaurants/{id}")
    public void deleteRestaurant(@PathVariable long id) {
        restaurantService.deleteRestaurant(id);
    }

    // Locations
    @GetMapping("locations")
    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("restaurants/{restaurantId}/locations")
    public List<Location> getLocationsByRestaurantId(@PathVariable Long restaurantId) {
        return locationService.getLocationsByRestaurantId(restaurantId);
    }

    @GetMapping("locations/{id}")
    public Location getLocation(@PathVariable long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping("locations")
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @PutMapping("locations/{id}")
    public Location updateLocation(
            @PathVariable long id,
            @RequestBody UpdateLocationRequest location
    ) {
        return  locationService.updateLocation(id, location);
    }

    @DeleteMapping("locations/{id}")
    public String deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }

    // Categories
    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("restaurants/{restaurantId}/categories")
    public List<Category> getCategoriesByRestaurantId(@PathVariable Long restaurantId) {
        return categoryService.getCategoriesByRestaurantId(restaurantId);
    }

    @GetMapping("categories/{id}")
    public Category getCategory(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("categories")
    public Category createCategory(@RequestBody Category location) {
        return categoryService.createCategory(location);
    }

    @PutMapping("categories/{id}")
    public Category updateLocation(@PathVariable long id, @RequestBody UpdateCategoryRequest category) {
        return  categoryService.updateCategory(id, category);
    }

    @DeleteMapping("categories/{id}")
    public String deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
