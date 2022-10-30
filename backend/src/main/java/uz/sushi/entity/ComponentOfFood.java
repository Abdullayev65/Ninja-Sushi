package uz.sushi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Table(name = "component_of_meal")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComponentOfFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

}