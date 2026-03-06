package week4.lab2a.tuswadlab42a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week4.lab2a.tuswadlab42a.domain.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public List<Category> findByRestaurantId(Long restaurantId);
}
