package uz.sushi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.enums.OrderStatusEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    private User currier;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OrderStatusEnum statusEnum;

    @Column(nullable = false)
    private boolean cash;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<OrderSet> orderSets = new HashSet<>();

    private Float deliverySum;

    private Float overAllSum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(columnDefinition = "text")
    private String comment;

    private Long orderedAt;

    private Long clientReceivedAt;

    private Long cancelledAt;

    private Long orderDeadline;

    public Order(User client) {
        this.client = client;
        address = client.getAddress();
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        float sum = 0;
        if (orderProducts != null)
            for (var item : orderProducts)
                sum += item.calculatePrice();
        if (orderSets != null)
            for (var item : orderSets)
                sum += item.calculatePrice();
        this.overAllSum = sum;
    }

    public Integer addProductQuantity(Integer productId, Integer count) {
        Set<OrderProduct> orderProducts = getOrderProducts();
        for (OrderProduct orderProduct : orderProducts)
            if (orderProduct.getProduct().getId().equals(productId)) {
                count += orderProduct.getQuantity();
                if (count <= 0) {
                    orderProducts.remove(orderProduct);
                    return 0;
                } else orderProduct.setQuantity(count.shortValue());
                return count;
            }
        return null;
    }

    public Integer addSetQuantity(Integer setId, Integer count) {
        Set<OrderSet> orderSets = getOrderSets();
        for (OrderSet orderSet : orderSets)
            if (orderSet.getSetOfProduct().getId().equals(setId)) {
                count += orderSet.getQuantity();
                if (count <= 0) {
                    orderSets.remove(orderSet);
                    return 0;
                } else orderSet.setQuantity(count.shortValue());
                return count;
            }
        return null;
    }


}
