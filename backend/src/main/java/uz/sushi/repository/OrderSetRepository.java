package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.OrderProduct;
import uz.sushi.entity.OrderSet;

public interface OrderSetRepository extends JpaRepository<OrderSet, Long> {

}
