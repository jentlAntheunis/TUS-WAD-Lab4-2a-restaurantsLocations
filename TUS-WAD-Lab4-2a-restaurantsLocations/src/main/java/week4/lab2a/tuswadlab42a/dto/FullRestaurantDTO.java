package week4.lab2a.tuswadlab42a.dto;

import lombok.Data;
import week4.lab2a.tuswadlab42a.domain.Location;
import week4.lab2a.tuswadlab42a.domain.Category;

import java.util.List;

@Data
public class FullRestaurantDTO {
    private String name;
    private String phone;
    private List<Location> locations;
    private List<Category> categories;
}
