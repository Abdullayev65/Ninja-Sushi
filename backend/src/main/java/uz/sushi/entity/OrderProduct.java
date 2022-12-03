package uz.sushi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.utill.Calculate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Product product;

    @Column(nullable = false)
    private Short quantity;

    @Column(nullable = false)
    private Float unitPrice;

    public OrderProduct(Order order, Product product, Short quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = Calculate.moneyToFloat(product.getPrice());
    }

    public float calculatePrice() {
        return unitPrice == null || quantity == null ? 0 : unitPrice * quantity;
    }


    public static OrderProduct mapping(Product product, Order order, Number quantity) {
        return new OrderProduct(order, product, quantity.shortValue());
    }

}
