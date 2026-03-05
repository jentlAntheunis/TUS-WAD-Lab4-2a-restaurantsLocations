package week4.lab2a.tuswadlab42a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week4.lab2a.tuswadlab42a.domain.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {
    public List<Location> findByRestaurantId(Long id);

}
