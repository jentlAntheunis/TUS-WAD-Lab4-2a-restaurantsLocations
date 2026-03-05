package week4.lab2a.tuswadlab42a.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import week4.lab2a.tuswadlab42a.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
