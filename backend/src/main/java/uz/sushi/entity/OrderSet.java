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
public class OrderSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SetOfProduct setOfProduct;

    @Column(nullable = false)
    private Short quantity;

    @Column(nullable = false)
    private Float unitPrice;

    public OrderSet(Order order, SetOfProduct setOfProduct, Short quantity, Float unitPrice) {
        this.order = order;
        this.setOfProduct = setOfProduct;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public static OrderSet mapping(SetOfProduct setOfProduct, Order order, Integer count) {
        return new OrderSet(order,setOfProduct,count.shortValue(), Calculate.moneyToFloat(setOfProduct.getPrice()));
    }

    public float calculatePrice() {
        return unitPrice == null || quantity == null ? 0 : unitPrice * quantity;
    }

}
