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
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
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

    public Location createLocation(Location location) {

        return locationRepository.save(location);
    }

    public String deleteLocation(Long id) {
        Location location = locationRepository.getReferenceById(id);
        locationRepository.delete(location);
        return "Location deleted";
    }
}
