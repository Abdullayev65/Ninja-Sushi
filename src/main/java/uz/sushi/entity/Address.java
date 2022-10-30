package uz.sushi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;
    @Column
    private String house;
    @Column
    private String entrance;
    @Column
    private String building;
    @Column
    private String apartment;
    @Column
    private String floor;

}
