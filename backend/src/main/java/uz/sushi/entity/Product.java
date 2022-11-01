package uz.sushi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.sushi.entity.enums.ProductType;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ComponentOfFood> components;

    private String weight;

    private String price;

    private String imagUrl;

}
