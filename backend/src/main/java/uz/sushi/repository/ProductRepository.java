package uz.sushi.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.Product;
import uz.sushi.entity.enums.ProductType;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByTypeIs(ProductType type, Pageable pageable);

    Product findByName(String name);

    boolean existsByName(String name);
}
