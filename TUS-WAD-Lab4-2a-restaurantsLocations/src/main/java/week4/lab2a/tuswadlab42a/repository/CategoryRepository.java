package week4.lab2a.tuswadlab42a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import week4.lab2a.tuswadlab42a.domain.Category;

public interface CategoryRepository extends JpaRepository<Long, Category> {
}
