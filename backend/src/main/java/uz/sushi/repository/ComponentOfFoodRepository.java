package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.ComponentOfFood;

import java.util.Optional;

public interface ComponentOfFoodRepository extends JpaRepository<ComponentOfFood, Integer> {
    boolean existsByName(String name);

    Optional<ComponentOfFood> findByName(String name);
}
