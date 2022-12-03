package uz.sushi.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.OrderProduct;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderProductDTO {

    private Long id;

    private Long orderId;

    private Integer productId;

    private Short quantity;

    private Float unitPrice;

    private String name;

    private String imagUrl;

    public OrderProductDTO(OrderProduct orderProduct) {
        id = orderProduct.getId();
        orderId = orderProduct.getOrder().getId();
        productId = orderProduct.getProduct().getId();
        quantity = orderProduct.getQuantity();
        unitPrice = orderProduct.getUnitPrice();
        name = orderProduct.getProduct().getName();
        imagUrl = orderProduct.getProduct().getImagUrl();
    }

    public static OrderProductDTO mapper(OrderProduct orderProduct) {
        return new OrderProductDTO(orderProduct);
    }

}
