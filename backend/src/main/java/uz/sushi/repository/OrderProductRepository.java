package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
