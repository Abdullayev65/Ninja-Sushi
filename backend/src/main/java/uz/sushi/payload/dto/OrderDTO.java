package uz.sushi.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.Order;
import uz.sushi.entity.enums.OrderStatusEnum;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private Long id;

    private OrderStatusEnum statusEnum;

    private boolean cash;

    private Set<OrderProductDTO> orderProducts = new HashSet<>();

    private Float deliverySum;

    private Float overAllSum;

    private AddressDTO address;

    private String comment;

    private Long orderedAt;

    private Long clientReceivedAt;

    private Long cancelledAt;

    private Long orderDeadline;

    public OrderDTO(Order order) {
        id = order.getId();
        statusEnum = order.getStatusEnum();
        cash = order.isCash();
        orderProducts = order.getOrderProducts().stream()
                .map(OrderProductDTO::mapper).collect(Collectors.toSet());
        deliverySum = order.getDeliverySum();
        overAllSum = order.getOverAllSum();
        address = AddressDTO.mapping(order.getAddress());
        comment = order.getComment();
        orderedAt = order.getOrderedAt();
        clientReceivedAt = order.getClientReceivedAt();
        cancelledAt = order.getCancelledAt();
        orderDeadline = order.getOrderDeadline();
    }

    public static OrderDTO mapping(Order order) {
        return new OrderDTO(order);
    }

}
