package week4.lab2a.tuswadlab42a.api;

import org.springframework.web.bind.annotation.*;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Restaurant;
import week4.lab2a.tuswadlab42a.service.LocationService;
import week4.lab2a.tuswadlab42a.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins="http://localhost:5173")
public class AdminController {
    private final LocationService locationService;
    private final RestaurantService restaurantService;
    public AdminController(LocationService locationService, RestaurantService restaurantService) {
        this.locationService = locationService;
        this.restaurantService = restaurantService;
    }

    // Locations
    @GetMapping("/locations")
    public List<Location> getLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/locations/{id}")
    public Location getLocation(@PathVariable long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping("/locations")
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @DeleteMapping("/locations/{id}")
    public String deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }

    // Restaurants
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantsByLocation(@RequestParam long locationId) {
        if(locationId != 0){
            return restaurantService.findByLocationId(locationId);
        }
        return restaurantService.findAll();
    }

    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        System.out.println(restaurant.toString());
        return restaurantService.createRestaurant(restaurant);
    }

    @DeleteMapping("/restaurants?{id}")
    public void deleteRestaurant(@PathVariable long id) {
        restaurantService.deleteRestaurant(id);
    }
}
