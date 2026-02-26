package week4.lab2a.tuswadlab42a.service;

import org.springframework.stereotype.Service;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.repository.LocationRepository;
import week4.lab2a.tuswadlab42a.repository.RestaurantRepository;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final RestaurantRepository restaurantRepository;
    public LocationService(LocationRepository locationRepository,  RestaurantRepository restaurantRepository) {
        this.locationRepository = locationRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long locationId) {
        return locationRepository.getReferenceById(locationId);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public String deleteLocation(Long id) {
        Location location = locationRepository.getReferenceById(id);
        // check if no restaurants in location
        List<Restaurant> restaurants = restaurantRepository.findByLocationLocationId(id);
        if (restaurants.isEmpty()) {
            locationRepository.delete(location);
            return "Location deleted";
        }
        return "Location not deleted, restaurants inside";
    }
}
