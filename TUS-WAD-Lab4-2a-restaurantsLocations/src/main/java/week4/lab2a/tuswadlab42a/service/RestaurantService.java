package week4.lab2a.tuswadlab42a.service;

import org.springframework.stereotype.Service;
import week4.lab2a.tuswadlab42a.domain.Category;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.dto.FullRestaurantDTO;
import week4.lab2a.tuswadlab42a.dto.RestaurantDTO;
import week4.lab2a.tuswadlab42a.repository.CategoryRepository;
import week4.lab2a.tuswadlab42a.repository.LocationRepository;
import week4.lab2a.tuswadlab42a.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public FullRestaurantDTO findById(Long id) {
        Restaurant restaurant = restaurantRepository.getReferenceById(id);
        List<Location> locations = locationRepository.findByRestaurantId(restaurant.getId());
        List<Category> categories = categoryRepository.findByRestaurantId(restaurant.getId());

        FullRestaurantDTO restaurantDTO = new FullRestaurantDTO();
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setPhone(restaurant.getPhone());
        restaurantDTO.setLocations(locations);
        restaurantDTO.setCategories(categories);
        return  restaurantDTO;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long restaurantId, RestaurantDTO req) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found")); // ensure location exists

        if (req.getName() != null) restaurant.setName(req.getName());
        if (req.getPhone() != null) restaurant.setPhone(req.getPhone());

        return restaurantRepository.save(restaurant);
    }

    public String deleteRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.getReferenceById(id);
        // check if no locations in restaurant
        List<Location> locations = locationRepository.findByRestaurantId(id);
        if (locations.isEmpty()) {
            restaurantRepository.delete(restaurant);
            return "Restaurant deleted";
        }
        return "Restaurant not deleted, locations inside";
    }
}
