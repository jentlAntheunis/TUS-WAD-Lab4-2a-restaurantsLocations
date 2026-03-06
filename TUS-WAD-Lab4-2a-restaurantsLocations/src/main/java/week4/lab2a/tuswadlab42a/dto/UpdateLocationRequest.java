package week4.lab2a.tuswadlab42a.dto;

import lombok.Data;

@Data
public class UpdateLocationRequest {
    private Long restaurantId; // required so we can ensure restaurant is in that location
    private String city;
}
