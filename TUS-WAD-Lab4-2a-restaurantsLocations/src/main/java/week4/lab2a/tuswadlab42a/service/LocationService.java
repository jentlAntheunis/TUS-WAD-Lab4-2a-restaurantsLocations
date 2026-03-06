package week4.lab2a.tuswadlab42a.service;

import org.springframework.stereotype.Service;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.dto.LocationDTO;
import week4.lab2a.tuswadlab42a.repository.LocationRepository;
import week4.lab2a.tuswadlab42a.repository.RestaurantRepository;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final RestaurantRepository restaurantRepository;

    public LocationService(LocationRepository locationRepository, RestaurantRepository restaurantRepository) {
        this.locationRepository = locationRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public List<Location> getLocationsByRestaurantId(Long restaurantId) {
        return locationRepository.findByRestaurantId(restaurantId);
    }

    public Location getLocationById(Long locationId) {

        return locationRepository.getReferenceById(locationId);
    }

    public Location createLocation(LocationDTO req) {
        Location newLocation = new Location();
        if(req.getCity() != null) newLocation.setCity(req.getCity());
        if(req.getRestaurantId() != null && restaurantRepository.findById(req.getRestaurantId()).isPresent()) {
            newLocation.setRestaurant(restaurantRepository.findById(req.getRestaurantId()).get());
        }
        return locationRepository.save(newLocation);
    }

    public Location updateLocation(Long locationId, LocationDTO req) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found")); // ensure location exists
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found")); // enforce "update by restaurant" (location must belong to that restaurant)

        if (!location.getRestaurant().getId().equals(restaurant.getId())) {
            throw new RuntimeException("Location does not belong to the given location"); } // update fields (only if provided) if (req.getName() != null)

        if(req.getCity() != null) location.setCity(req.getCity());
        return locationRepository.save(location);
    }

    public String deleteLocation(Long id) {
        Location location = locationRepository.getReferenceById(id);
        locationRepository.delete(location);
        return "Location deleted";
    }
}
