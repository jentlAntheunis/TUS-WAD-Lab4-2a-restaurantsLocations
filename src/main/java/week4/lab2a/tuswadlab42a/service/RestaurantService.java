package week4.lab2a.tuswadlab42a.service;

import org.springframework.stereotype.Service;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant findById(Long id) {
        return restaurantRepository.getReferenceById(id);
    }

    public List<Restaurant> findByLocationId(Long locationId) {
        return restaurantRepository.findByLocationLocationId(locationId);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        System.out.println(restaurant.getLocation());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = findById(restaurantId);
        restaurantRepository.delete(restaurant);
    }
}
