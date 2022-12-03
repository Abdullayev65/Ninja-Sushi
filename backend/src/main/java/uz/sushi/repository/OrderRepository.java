package uz.sushi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.sushi.entity.Order;
import uz.sushi.entity.User;
import uz.sushi.entity.enums.OrderStatusEnum;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderByClientIsAndStatusEnumIs(User client, OrderStatusEnum statusEnum);

}
