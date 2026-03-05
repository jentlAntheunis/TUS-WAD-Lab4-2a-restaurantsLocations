package week4.lab2a.tuswadlab42a.service;

import org.springframework.stereotype.Service;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.repository.LocationRepository;
import week4.lab2a.tuswadlab42a.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final LocationRepository locationRepository;
    public RestaurantService(RestaurantRepository restaurantRepository, LocationRepository locationRepository) {
        this.restaurantRepository = restaurantRepository;
        this.locationRepository = locationRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.getReferenceById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {

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
